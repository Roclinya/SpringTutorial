package com.tutorial.SpringTutorial.Controller;


import com.tutorial.SpringTutorial.Service.MemberCreateScheduler;
import com.tutorial.SpringTutorial.Service.MemberService;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import com.tutorial.SpringTutorial.vo.MemberResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/memberApi")
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MemberCreateScheduler memberCreateScheduler;

    public MemberController(MemberRepository memberRepository, MemberService memberService,
                            MemberCreateScheduler memberCreateScheduler) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.memberCreateScheduler = memberCreateScheduler;
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

    @PostMapping("/scheduler/trigger")
    public ResponseEntity<String> triggerSchedulerCreateMember() {
        memberCreateScheduler.triggerCreateMemberNow();
        return ResponseEntity.ok("Scheduler createMember flow triggered.");
    }


}
