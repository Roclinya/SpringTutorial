package com.tutorial.SpringTutorial.Controller;


import com.tutorial.SpringTutorial.entity.Member;
import com.tutorial.SpringTutorial.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/memberApi")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/findAllEMail")
    public ResponseEntity<List<String>> findAllEMail() {
        List<String> result = memberRepository.findAllEMail();
        return ResponseEntity.ok(result);
    }


}
