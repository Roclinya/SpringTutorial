package com.tutorial.SpringTutorial.Service.Impl;

import com.tutorial.SpringTutorial.Service.MemberService;
import com.tutorial.SpringTutorial.entity.Member;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import com.tutorial.SpringTutorial.vo.MemberResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RedissonClient redissonClient;

    @Value("${member.create-lock-test.enabled:false}")
    private boolean lockTestEnabled;

    @Value("${member.create-lock-test.target-usr-name:scheduler-lock-user}")
    private String lockTestUsrName;

    @Value("${member.create-lock-test.sleep-millis:60000}")
    private long lockTestSleepMillis;

    public MemberServiceImpl(MemberRepository memberRepository, RedissonClient redissonClient) {
        this.memberRepository = memberRepository;
        this.redissonClient = redissonClient;
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberCreateRequest request) {
        String normalizedUsrName = request.getUsrName().trim();
        String lockKey = "member:create:" + normalizedUsrName.toLowerCase();
        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = false;

        try {
            locked = lock.tryLock(5, 60000, TimeUnit.SECONDS);
            if (!locked) {
                throw new IllegalStateException("Unable to create member now, please retry.");
            }

            if (memberRepository.existsByUsrName(normalizedUsrName)) {
                throw new IllegalArgumentException("usrName already exists: " + normalizedUsrName);
            }

            // 測試用: 讓目標帳號在拿到鎖後故意停留，便於手動用同帳號呼叫 API 驗證鎖競爭。
            if (lockTestEnabled && normalizedUsrName.equalsIgnoreCase(lockTestUsrName) && lockTestSleepMillis > 0) {
                Thread.sleep(lockTestSleepMillis);
            }

            Member member = new Member();
            member.setUsrName(normalizedUsrName);
            member.seteMail(request.geteMail().trim());
            member.setUsrPwd(request.getUsrPwd().trim());

            Member savedMember = memberRepository.save(member);
            return new MemberResponse(savedMember.getId(), savedMember.getUsrName(), savedMember.geteMail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while acquiring Redisson lock.", e);
        } finally {
            if (locked && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}

