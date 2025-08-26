package com.tutorial.SpringTutorial.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tutorial.SpringTutorial.Service.RecaptchaService;
import com.tutorial.SpringTutorial.entity.Recaptcha.RecaptchaReq;
import com.tutorial.SpringTutorial.entity.Recaptcha.RecaptchaRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class RecaptchaController {

    @Autowired
    RecaptchaService recaptchaService;

    @PostMapping("/validateRecaptchaToken")
    public RecaptchaRes validateRecaptchaToken(@RequestBody RecaptchaReq req) throws JsonProcessingException {
     return recaptchaService.callValidateTokenUrl(req);
    }
}
