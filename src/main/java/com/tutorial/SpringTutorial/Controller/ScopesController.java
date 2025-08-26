package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Service.HelloMessageGenerator;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;


@RequestMapping("/api")
@RestController
public class ScopesController {


    //Request Scope
    //https://www.baeldung.com/spring-bean-scopes

    //Config : define the bean with the request scope using the @Scope annotation here
    @Resource(name = "requestScopedBean")
    HelloMessageGenerator requestScopedBean;

    @PostMapping("/scopes/request")
    public String getRequestScopeMessage(final Model model) {
        model.addAttribute("previousMessage", requestScopedBean.getMessage());
        requestScopedBean.setMessage("Good morning!");
        model.addAttribute("currentMessage", requestScopedBean.getMessage());
        return "scopesExample";
    }
}
