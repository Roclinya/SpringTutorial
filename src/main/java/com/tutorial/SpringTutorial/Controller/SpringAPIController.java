package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Service.Impl.ChatModelImpl;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RequestMapping("/api")
@RestController
//@RequiredArgsConstructor
public class SpringAPIController {


    private final ChatModel chatModel;

    @Autowired
    public SpringAPIController(ChatModelImpl chatModel) {
        this.chatModel = chatModel;
    }
//
//
//    private final ChatClient chatClient;
//
//    public SpringAPIController(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }
//
//    @GetMapping("/ai")
//    ChatResponse generation(String userInput) {
//        return this.chatClient.prompt()
//                .user(userInput)
//                .call()
//                .chatResponse();
//    }
//
    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Prompt cannot be empty.");
        }
        try {
//            String response = chatModel.call(prompt.trim());
            String response = chatModel.call(prompt.trim());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/chatFlux")
    public Flux<String> chatFlux(@RequestParam String prompt) {
        Flux<String> resFlux = chatModel.stream(prompt);
        return resFlux;
    }

    @GetMapping(value = "/chat2")
    public String chat2(@RequestParam String prompt) {
        List<AbstractMessage> messages = List.of(
                new SystemMessage("你是一個說故事大師，如果找不到資料或是最近的新聞就編造一個聽起來讓人開心的消息"),
                new UserMessage(prompt)
        );
        ChatResponse response = chatModel
                .call(new Prompt(
                        (Message) messages,
                        (ChatOptions) OpenAiChatOptions.builder().model("GPT-4o")
                                .build()
                ));
        return response.getResult().getOutput().getContent();
    }
}
