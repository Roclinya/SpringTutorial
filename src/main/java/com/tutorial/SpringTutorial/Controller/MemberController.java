package com.tutorial.SpringTutorial.Controller;


import com.tutorial.SpringTutorial.Service.MemberCreateScheduler;
import com.tutorial.SpringTutorial.Service.MemberService;
import com.tutorial.SpringTutorial.Service.MemberUpdateScheduler;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import com.tutorial.SpringTutorial.vo.MemberResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/memberApi")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberCreateScheduler memberCreateScheduler;
    private final MemberUpdateScheduler memberUpdateScheduler;

    public MemberController(MemberRepository memberRepository, MemberService memberService,
                            MemberCreateScheduler memberCreateScheduler,
                            MemberUpdateScheduler memberUpdateScheduler) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.memberCreateScheduler = memberCreateScheduler;
        this.memberUpdateScheduler = memberUpdateScheduler;
    }

    @PostMapping("/findAllEMail")
    public ResponseEntity<List<String>> findAllEMail() {
        List<String> result = memberRepository.findAllEMail();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberCreateRequest request) {
        return ResponseEntity.ok(memberService.createMember(request));
    }

    @PostMapping("/create/slow-commit")
    public ResponseEntity<MemberResponse> createMemberWithSlowCommit(
            @Valid @RequestBody MemberCreateRequest request,
            @RequestParam(defaultValue = "30000") long delayMillis) {
        return ResponseEntity.ok(memberService.createMemberWithSlowCommit(request, delayMillis));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberResponse> updateMember(
            @PathVariable("id") Long id,
            @Valid @RequestBody MemberCreateRequest request,
            @RequestParam(defaultValue = "0") long holdLockMillis) {
        return ResponseEntity.ok(memberService.updateMember(id, request, holdLockMillis));
    }

    @PostMapping("/scheduler/trigger")
    public ResponseEntity<String> triggerSchedulerCreateMember() {
        memberCreateScheduler.triggerCreateMemberNow();
        return ResponseEntity.ok("Scheduler createMember flow triggered.");
    }

    @PostMapping("/scheduler/update/trigger")
    public ResponseEntity<String> triggerSchedulerUpdateMember() {
        memberUpdateScheduler.triggerUpdateMemberNow();
        return ResponseEntity.ok("Scheduler updateMember flow triggered.");
    }


}
