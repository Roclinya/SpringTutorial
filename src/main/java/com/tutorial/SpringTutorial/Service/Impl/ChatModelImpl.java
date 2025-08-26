package com.tutorial.SpringTutorial.Service.Impl;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

@Service // 或者使用 @Component
public class ChatModelImpl implements ChatModel {


    @Override
    public ChatResponse call(Prompt prompt) {
        ArrayList<Generation> list = new ArrayList<>();
        // 模擬處理 Prompt 並返回 ChatResponse
        // 從 Prompt 的指令中提取所有訊息內容 (getContent() 方法)
        // 使用 stream() 流處理和 reduce() 方法將所有訊息內容串接成一個字串，並以空格分隔。
        String inputContent = prompt.getInstructions().stream()
                .map(Message::getContent)
                .reduce("", (acc, msg) -> acc + " " + msg);
        String responseContent = "Echo: " + inputContent.trim();

        // 包裝回應
        Generation generation = new Generation(responseContent);
        list.add(generation);
        return new ChatResponse(list);
    }

    @Override
    public ChatOptions getDefaultOptions() {
        return null;
    }

    @Override
    public Flux<String> stream(String message) {
        return ChatModel.super.stream(message);
    }

    @Override
    public Flux<String> stream(Message... messages) {
        return ChatModel.super.stream(messages);
    }

    @Override
    public Flux<ChatResponse> stream(Prompt prompt) {
        return ChatModel.super.stream(prompt);
    }


}
