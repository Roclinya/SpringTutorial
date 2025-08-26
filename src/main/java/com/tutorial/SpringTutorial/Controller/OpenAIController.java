package com.tutorial.SpringTutorial.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tutorial.SpringTutorial.Service.ChatGPTService;
import com.tutorial.SpringTutorial.Service.DemoHttpService;
import com.tutorial.SpringTutorial.Service.HelloMessageGenerator;
import com.tutorial.SpringTutorial.openai.ChatRequest;
import com.tutorial.SpringTutorial.request.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/api")
@RestController
public class OpenAIController {
    @Autowired
    ChatGPTService chatGPTService;
    @GetMapping("/openAI")
    public String callChatGPT() {
        chatGPTService.createCompletion();
        return "scopesExample";
    }


    @PostMapping("/requestAPI")
    public void requestAPI(@RequestBody ChatRequest req) throws JsonProcessingException {
//        System.out.println(demoHttpService.requestAPI("探盤查計畫報告書有幾個項目"));
        String question01 = req.getMessages().get(0).getContent();
        System.out.println(chatGPTService.requestAPI(question01).getContent());
        System.out.println("------------------------------------------------------");
//        System.out.println(demoHttpService.requestAPI("可以根據列出的項目做詳細的報告嗎?").getContent());
        String question02 = req.getMessages().get(1).getContent();
        System.out.println(chatGPTService.requestAPI(question02).getContent());
        System.out.println("------------------------------------------------------");
//        System.out.println(demoHttpService.requestAPI("最後再做一個結論").getContent());
        String question03 = req.getMessages().get(2).getContent();
        System.out.println(chatGPTService.requestAPI(question03).getContent());

    }
    //http://127.0.0.1:8080/api/requestAPI

//    {
//        "model": "gpt-4o",
//            "messages": [
//        {
//            "role": "user",
//                "content": "Who won the world series in 2020?"
//        },
//        {
//            "role": "assistant",
//                "content": "The Los Angeles Dodgers won the World Series in 2020."
//        },
//        {
//            "role": "user",
//                "content": "Where was it played?"
//        }
//    ],
//        "temperature": 1,
//            "top_p": 1,
//            "n": 1,
//            "stream": false,
//            "max_tokens": 250,
//            "presence_penalty": 0,
//            "frequency_penalty": 0
//    }
}
