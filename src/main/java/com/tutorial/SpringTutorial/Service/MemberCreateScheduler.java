package com.tutorial.SpringTutorial.Service;

import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberCreateScheduler {

    private final MemberService memberService;

    @Value("${member.scheduler.enabled:false}")
    private boolean schedulerEnabled;

    @Value("${member.scheduler.test-usr-name:scheduler-lock-user}")
    private String testUsrName;

    @Value("${member.scheduler.test-email:scheduler-lock-user@example.com}")
    private String testEmail;

    @Value("${member.scheduler.test-password:123456}")
    private String testPassword;

    public MemberCreateScheduler(MemberService memberService) {
        this.memberService = memberService;
    }

    @Scheduled(cron = "${member.scheduler.cron:0 */5 * * * *}")
    @SchedulerLock(name = "memberCreateScheduler", lockAtMostFor = "PT10M", lockAtLeastFor = "PT1M")
    public void createMemberWithScheduler() {
        log.info("Scheduled task triggered: createMemberWithScheduler");
        if (!schedulerEnabled) {
            return;
        }

        createMemberForLockTest();
    }

    public void triggerCreateMemberNow() {
        createMemberForLockTest();
    }

    private void createMemberForLockTest() {
        MemberCreateRequest request = new MemberCreateRequest();
        request.setUsrName(testUsrName);
        request.seteMail(testEmail);
        request.setUsrPwd(testPassword);

        memberService.createMember(request);
    }
}

