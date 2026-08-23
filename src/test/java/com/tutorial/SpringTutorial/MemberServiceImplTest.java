package com.tutorial.SpringTutorial;

import com.tutorial.SpringTutorial.Service.Impl.MemberServiceImpl;
import com.tutorial.SpringTutorial.entity.Member;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import com.tutorial.SpringTutorial.vo.MemberResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.PessimisticLockingFailureException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    // 模擬資料庫存取層，避免測試時真的連 DB。
    @Mock
    private MemberRepository memberRepository;

    // 模擬 Redisson 客戶端，用來驗證分散式鎖流程是否正確。
    @Mock
    private RedissonClient redissonClient;

    // 模擬 lock 物件本身 (RLock)，可控制 tryLock / unlock 等行為。
    @Mock
    private RLock lock;

    // 將上述 @Mock 自動注入到 MemberServiceImpl，形成受測主體(SUT)。
    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void createMember_success() throws InterruptedException {
        // Arrange: 準備一筆合法的新增會員請求。
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName("larry");
        request.seteMail("larry@example.com");
        request.setUsrPwd("123456");

        // Arrange: 模擬 service 根據 usrName 取得對應 lock。
        when(redissonClient.getLock("member:create:larry")).thenReturn(lock);
        // Arrange: 模擬可在 5 秒內拿到鎖，鎖租期 15 秒。
        when(lock.tryLock(5, 15, TimeUnit.SECONDS)).thenReturn(true);
        // Arrange: 模擬資料庫中該 usrName 尚不存在。
        when(memberRepository.existsByUsrName("larry")).thenReturn(false);
        // Arrange: 模擬當前執行緒確實持有鎖，讓 finally 區塊可正常 unlock。
        when(lock.isHeldByCurrentThread()).thenReturn(true);
        // Arrange: 模擬 save 後由 DB 產生 id=100。
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> {
            Member member = invocation.getArgument(0);
            member.setId(100L);
            return member;
        });

        // Act: 執行新增會員。
        MemberResponse result = memberService.createMember(request);

        // Assert: 驗證回傳資料是預期值，代表流程成功完成。
        Assertions.assertEquals(100L, result.getId());
        Assertions.assertEquals("larry", result.getUsrName());
        Assertions.assertEquals("larry@example.com", result.geteMail());
    }

    @Test
    void createMember_duplicateUsrName() throws InterruptedException {
        // Arrange: 準備一筆新增請求，但稍後會模擬使用者名稱重複。
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName("larry");
        request.seteMail("larry@example.com");
        request.setUsrPwd("123456");

        // Arrange: 仍可取得鎖，但 DB 檢查為重複。
        when(redissonClient.getLock("member:create:larry")).thenReturn(lock);
        when(lock.tryLock(5, 15, TimeUnit.SECONDS)).thenReturn(true);
        when(lock.isHeldByCurrentThread()).thenReturn(true);
        when(memberRepository.existsByUsrName("larry")).thenReturn(true);

        // Assert: 預期 service 丟出 IllegalArgumentException，防止重複會員被建立。
        Assertions.assertThrows(IllegalArgumentException.class, () -> memberService.createMember(request));
    }

    @Test
    void createMember_lockNotReleasedByOtherProcess_shouldNotWriteNewData() throws InterruptedException {
        // Arrange: 模擬另一個節點/執行緒長時間持有鎖，導致本次請求在等待後仍無法取得鎖。
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName("larry");
        request.seteMail("larry@example.com");
        request.setUsrPwd("123456");

        when(redissonClient.getLock("member:create:larry")).thenReturn(lock);
        // tryLock 回傳 false 代表在 waitTime 內沒有拿到鎖，等同「鎖未釋放」。
        when(lock.tryLock(5, 15, TimeUnit.SECONDS)).thenReturn(false);

        // Act + Assert: 預期直接失敗，並回傳可重試的錯誤。
        IllegalStateException ex = Assertions.assertThrows(
                IllegalStateException.class,
                () -> memberService.createMember(request)
        );
        Assertions.assertEquals("Unable to create member now, please retry.", ex.getMessage());

        // Assert: 因為沒拿到鎖，後續資料庫查詢/寫入都不應發生。
        verify(memberRepository, never()).existsByUsrName(any(String.class));
        verify(memberRepository, never()).save(any(Member.class));
        // Assert: 沒持有鎖時，finally 不應呼叫 unlock。
        verify(lock, never()).unlock();
    }

    @Test
    void createMember_lockAcquireInterrupted_shouldThrowAndKeepInterruptFlag() throws InterruptedException {
        // Arrange: 模擬 tryLock 過程被中斷。
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName("larry");
        request.seteMail("larry@example.com");
        request.setUsrPwd("123456");

        when(redissonClient.getLock("member:create:larry")).thenReturn(lock);
        when(lock.tryLock(5, 15, TimeUnit.SECONDS)).thenThrow(new InterruptedException("interrupted"));

        // Act + Assert: service 應轉成 IllegalStateException，並帶出固定錯誤訊息。
        IllegalStateException ex = Assertions.assertThrows(
                IllegalStateException.class,
                () -> memberService.createMember(request)
        );
        Assertions.assertEquals("Interrupted while acquiring Redisson lock.", ex.getMessage());

        // Assert: 中斷旗標應被保留，避免吞掉 interrupt 狀態。
        Assertions.assertTrue(Thread.currentThread().isInterrupted());
        Thread.interrupted(); // 清除中斷旗標，避免影響其他測試。

        // Assert: 因為鎖未成功取得，不應觸發 DB 寫入流程。
        verify(memberRepository, never()).existsByUsrName(any(String.class));
        verify(memberRepository, never()).save(any(Member.class));
        verify(lock, never()).unlock();
    }

    @Test
    void updateMember_success() {
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName("new-name");
        request.seteMail("new@example.com");
        request.setUsrPwd("new-pwd");

        Member existing = new Member();
        existing.setId(1L);
        existing.setUsrName("old-name");
        existing.seteMail("old@example.com");
        existing.setUsrPwd("old-pwd");

        when(memberRepository.findByIdForUpdate(1L)).thenReturn(Optional.of(existing));
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MemberResponse result = memberService.updateMember(1L, request, 0L);

        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("new-name", result.getUsrName());
        Assertions.assertEquals("new@example.com", result.geteMail());
    }

    @Test
    void updateMember_pessimisticLockFailure_shouldPropagate() {
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName("new-name");
        request.seteMail("new@example.com");
        request.setUsrPwd("new-pwd");

        when(memberRepository.findByIdForUpdate(1L))
                .thenThrow(new PessimisticLockingFailureException("Lock wait timeout"));

        Assertions.assertThrows(
                PessimisticLockingFailureException.class,
                () -> memberService.updateMember(1L, request, 0L)
        );
    }
}

