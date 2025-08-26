package com.tutorial.SpringTutorial.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.tutorial.SpringTutorial.openai.Answer;
import com.tutorial.SpringTutorial.openai.ChatResponse;
import com.tutorial.SpringTutorial.openai.Choice;
import com.tutorial.SpringTutorial.openai.Message;
import com.tutorial.SpringTutorial.request.RequestObject;
import com.tutorial.SpringTutorial.request.ResponseObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class DemoHttpService {

    public void httpDemo() throws JsonProcessingException {

        //測試網站 https://jsonplaceholder.typicode.com/
        String getUrl = "https://jsonplaceholder.typicode.com/todos/1";

        String postUrl = "http://jsonplaceholder.typicode.com/posts";

        RequestObject requestObject = new RequestObject();

        requestObject.setName("XXX");

        requestObject.setPhoneNo("0987654321");

        requestObject.setEmail("Demo@demo.demo");

        ObjectMapper objectMapper = new ObjectMapper();

        //http body
        String requestBody = objectMapper.writeValueAsString(requestObject);


//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);


//        ResponseObject responseObject = demoHttpGetURLConnection(getUrl, requestBody);
        ResponseEntity<String> responseEntity = demoHttpPostURLConnection(postUrl, requestObject);

        String body = responseEntity.getBody(); // 获取响应体
        System.out.println("HTTP 響應body：" + body);

        //以下是postForEntity比postForObject多出来的内容
        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode(); // 获取响应码
        int statusCodeValue = responseEntity.getStatusCodeValue(); // 获取响应码值
        HttpHeaders headers = responseEntity.getHeaders(); // 获取响应头

        System.out.println("HTTP 響應狀態：" + statusCode);
        System.out.println("HTTP 響應狀態碼：" + statusCodeValue);
        System.out.println("HTTP Headers訊息：" + headers);


    }
    private ResponseEntity<String> demoHttpPostURLConnection(String url, RequestObject requestObject) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestObject, String.class);
        return responseEntity;
    }
    private ResponseObject demoHttpGetURLConnection(String url, String requestBody) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseObject responseObject = restTemplate.getForEntity(url, ResponseObject.class).getBody();

        return responseObject;
    }


}
