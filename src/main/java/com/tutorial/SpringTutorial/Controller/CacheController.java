package com.tutorial.SpringTutorial.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.SpringTutorial.cache.TsmpDpItemsCacheProxy;
import com.tutorial.SpringTutorial.entity.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/api")
@RestController
public class CacheController {

    private final TsmpDpItemsCacheProxy tsmpDpItemsCacheProxy;

    public CacheController(TsmpDpItemsCacheProxy tsmpDpItemsCacheProxy) {
        this.tsmpDpItemsCacheProxy = tsmpDpItemsCacheProxy;
    }

    @PostMapping("/com/tutorial/SpringTutorial/cache")
    public void cache() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();


        Optional<Member> memberOpt = tsmpDpItemsCacheProxy.findById(2L);
        if(memberOpt.isPresent()){
//            System.out.println(memberOpt.get());
            // User Object 轉 json
            String json = objectMapper.writeValueAsString(memberOpt.get());
            System.out.println(json);
        }
    }
}
