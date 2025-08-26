package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class GenAIController {

    @Autowired
    ChatService chatService;

    public GenAIController(ChatService chatService) {
        this.chatService = chatService;
    }


    @GetMapping("/ask-ai")
    public  String getResponse(String prompt){
        return chatService.getResponse(prompt);
    }
}
