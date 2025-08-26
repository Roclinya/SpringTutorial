package com.tutorial.SpringTutorial.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.tutorial.SpringTutorial.openai.Answer;
import com.tutorial.SpringTutorial.openai.ChatResponse;
import com.tutorial.SpringTutorial.openai.Choice;
import com.tutorial.SpringTutorial.openai.Message;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {

    private final static String OPENAI_API_DEFAULT_MODEL = "gpt-3.5-turbo";
    private final static String OPENAI_API_CHAT_URL = "https://api.openai.com/v1/chat/completions";

    private final static String OPENAI_KEY = System.getenv("OPENAI_GPT_KEY");;
    private final static String OPENAI_ORGANIZATION ="org-a0Xh1fg2u02eDC8cZqUISet5";

    private ObjectMapper objectMapper = null;

    private List<Message> __cHistorys =  new ArrayList<>();;  //問答訊息歷史清單
    public void createCompletion(){

//        RestClient defaultClient = RestClient.create();
//
//        RestClient customClient = RestClient.builder()
//                .requestFactory(new HttpComponentsClientHttpRequestFactory())
//                .messageConverters(converters -> converters.add(new MyCustomMessageConverter()))
//                .baseUrl("https://example.com")
//                .defaultUriVariables(Map.of("variable", "foo"))
//                .defaultHeader("My-Header", "Foo")
//                .requestInterceptor(myCustomInterceptor)
//                .requestInitializer(myCustomInitializer)
//                .build();

    }

    public Answer requestAPI(String question) throws JsonProcessingException {
        Answer cAnswer = null;
        try {
            HttpHeaders header = new HttpHeaders();
            header.set("Accept", "application/json");
            header.set("Content-Type", "application/json");
            header.set("Authorization", "Bearer " + OPENAI_KEY);   //API_KEY
            header.set("OpenAI-Organization", OPENAI_ORGANIZATION);   //組織代碼
//            header.setBearerAuth(OPENAI_KEY);


            //加入問題到歷史清單內(這樣可以讓 OpenAI 有前後文對照)
            addMessage(question);

            Map<String, Object> body = new HashMap<>();
            body.put("model", OPENAI_API_DEFAULT_MODEL);
            body.put("temperature", 0.7);
            body.put("messages", __cHistorys);  //直接傳入整個歷史清單給 OpenAI 讓他有前後文對照

            cAnswer = new Answer(0, "");

            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 設定序列化行為，使得只有非 null 的屬性會被包含在生成的 JSON 中
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE); // 設定命名策略，將 Java 的屬性名稱轉換為 snake_case 格式
            String sBody = objectMapper.writeValueAsString(body);

//            HttpEntity<String> httpEntity = new HttpEntity<>(sBody, header);

            HttpEntity<String> request = new HttpEntity<String>(sBody, header);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(OPENAI_API_CHAT_URL, request, String.class);
            ChatResponse chatResponse = objectMapper.readValue(responseEntity.getBody(), ChatResponse.class);


            List<Choice> cChoices = chatResponse.getChoices();
            if (!cChoices.isEmpty()) {
                Choice cChoice = cChoices.get(0);
                Message cMessage = cChoice.getMessage();
                if (cMessage != null) {
                    cAnswer.setStatusCode(responseEntity.getStatusCode().value());
                    cAnswer.setContent(cMessage.getContent());  //取得回答內容

                    //加入回答到歷史清單內(這樣可以讓 OpenAI 有前後文對照)
                    __cHistorys.add(cMessage);
                }
            }
        } catch (HttpClientErrorException e) {
            System.out.println("****** ERROR *********** " + e.getMostSpecificCause());
            throw e;
        }
        return cAnswer;
    }

    private void addMessage(String question) {
        Message cMessage = new Message();
        cMessage.setRole("user");
        cMessage.setContent(question);;

        __cHistorys.add(cMessage);
    }
}
