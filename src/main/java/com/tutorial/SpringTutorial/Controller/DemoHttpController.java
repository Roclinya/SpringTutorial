package com.tutorial.SpringTutorial.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tutorial.SpringTutorial.Service.DemoHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class DemoHttpController {

    @Autowired
    DemoHttpService demoHttpService;

    @PostMapping("/demoHttp")
    public void demoHttp() throws JsonProcessingException {
        demoHttpService.httpDemo();
    }
}
