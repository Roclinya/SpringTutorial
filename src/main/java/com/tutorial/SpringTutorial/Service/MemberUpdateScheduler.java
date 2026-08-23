package com.tutorial.SpringTutorial.Service;

import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberUpdateScheduler {

    private final MemberService memberService;

    @Value("${member.update-scheduler.enabled:false}")
    private boolean enabled;

    @Value("${member.update-scheduler.member-id:1}")
    private Long memberId;

    @Value("${member.update-scheduler.hold-lock-millis:120000}")
    private long holdLockMillis;

    @Value("${member.update-scheduler.usr-name:scheduler-lock-user}")
    private String usrName;

    @Value("${member.update-scheduler.email:scheduler-lock-user@example.com}")
    private String email;

    @Value("${member.update-scheduler.password:123456}")
    private String password;

    public MemberUpdateScheduler(MemberService memberService) {
        this.memberService = memberService;
    }

    @Scheduled(cron = "${member.update-scheduler.cron:0 */5 * * * *}")
    @SchedulerLock(name = "memberUpdateScheduler", lockAtMostFor = "PT10M", lockAtLeastFor = "PT30S")
    public void updateMemberWithScheduler() {
        if (!enabled) {
            return;
        }
        log.info("Scheduled task triggered: updateMemberWithScheduler, memberId={}, holdLockMillis={}", memberId, holdLockMillis);
        updateMemberForLockTest();
    }

    public void triggerUpdateMemberNow() {
        log.info("Manual trigger: updateMemberWithScheduler, memberId={}, holdLockMillis={}", memberId, holdLockMillis);
        updateMemberForLockTest();
    }

    private void updateMemberForLockTest() {
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName(usrName);
        request.seteMail(email);
        request.setUsrPwd(password);
        memberService.updateMember(memberId, request, holdLockMillis);
    }
}


