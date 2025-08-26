package com.tutorial.SpringTutorial.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.ContentCachingRequestWrapper;


@RequestMapping("/api")
@RestController
public class ContentCachingController {
    @PostMapping("/caching")
    public String hello(@RequestBody String id, HttpServletRequest request) {
        ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
        String requestBody = new String(requestWrapper.getContentAsByteArray());
        return "body： " + requestBody;
    }
}
