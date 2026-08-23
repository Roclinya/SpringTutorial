package com.tutorial.SpringTutorial.Service.Impl;

import com.tutorial.SpringTutorial.Service.MemberService;
import com.tutorial.SpringTutorial.entity.Member;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import com.tutorial.SpringTutorial.vo.MemberResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RedissonClient redissonClient;

    public MemberServiceImpl(MemberRepository memberRepository, RedissonClient redissonClient) {
        this.memberRepository = memberRepository;
        this.redissonClient = redissonClient;
    }

    @Override
    @Transactional
    public MemberResponse createMember(MemberCreateRequest request) {
        return createMemberInternal(request, null);
    }

    @Override
    @Transactional
    public MemberResponse createMemberWithSlowCommit(MemberCreateRequest request, long commitDelayMillis) {
        long safeDelayMillis = Math.max(commitDelayMillis, 0L);
        return createMemberInternal(request, safeDelayMillis);
    }

    @Override
    @Transactional
    public MemberResponse updateMember(Long memberId, MemberCreateRequest request, long holdLockMillis) {
        Member member = memberRepository.findByIdForUpdate(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found: " + memberId));

        member.setUsrName(request.getUsrName().trim());
        member.seteMail(request.geteMail().trim());
        member.setUsrPwd(request.getUsrPwd().trim());

        long safeDelayMillis = Math.max(holdLockMillis, 0L);
        if (safeDelayMillis > 0) {
            try {
                // Hold the transaction and DB row lock intentionally for lock-contention testing.
                Thread.sleep(safeDelayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while holding DB lock.", e);
            }
        }

        Member savedMember = memberRepository.save(member);
        return new MemberResponse(savedMember.getId(), savedMember.getUsrName(), savedMember.geteMail());
    }

    private MemberResponse createMemberInternal(MemberCreateRequest request, Long commitDelayMillis) {
        String normalizedUsrName = request.getUsrName().trim();
        String lockKey = "member:create:" + normalizedUsrName.toLowerCase();
        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = false;

        try {
            locked = lock.tryLock(5, 15, TimeUnit.SECONDS);
            if (!locked) {
                throw new IllegalStateException("Unable to create member now, please retry.");
            }

            if (memberRepository.existsByUsrName(normalizedUsrName)) {
                throw new IllegalArgumentException("usrName already exists: " + normalizedUsrName);
            }

            Member member = new Member();
            member.setUsrName(normalizedUsrName);
            member.seteMail(request.geteMail().trim());
            member.setUsrPwd(request.getUsrPwd().trim());

            Member savedMember = memberRepository.save(member);

            // Keep transaction open to simulate a slow commit and make lock contention easier to reproduce.
            if (commitDelayMillis != null && commitDelayMillis > 0) {
                Thread.sleep(commitDelayMillis);
            }

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

