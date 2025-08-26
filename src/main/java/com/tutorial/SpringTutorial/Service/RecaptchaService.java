package com.tutorial.SpringTutorial.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.tutorial.SpringTutorial.entity.Recaptcha.RecaptchaReq;
import com.tutorial.SpringTutorial.entity.Recaptcha.RecaptchaRes;
import com.tutorial.SpringTutorial.openai.ChatResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecaptchaService {

    private final static String VALIDATE_TOKEN_URL = "https://www.google.com/recaptcha/api/siteverify";

    private ObjectMapper objectMapper = null;
    public RecaptchaRes callValidateTokenUrl(RecaptchaReq req) throws JsonProcessingException {
        RecaptchaRes recaptchaResponse =null;
        try {
            HttpHeaders header = new HttpHeaders();
            header.set("Accept", "application/json");
            header.set("Content-Type", "application/x-www-form-urlencoded");
//          header.set("Authorization", "Bearer " + OPENAI_KEY);   //API_KEY

            Map<String, Object> body = new HashMap<>();
            body.put("secret", req.getSecret());
            body.put("response", req.getResponse());


            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // 屬性為NULL的不序列化
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
            String sBody = objectMapper.writeValueAsString(body);

            HttpEntity<String> request = new HttpEntity<String>(sBody, header);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(VALIDATE_TOKEN_URL, request, String.class);
             recaptchaResponse = objectMapper.readValue(responseEntity.getBody(), RecaptchaRes.class);


        } catch (HttpClientErrorException | JsonProcessingException e) {
            System.out.println("****** ERROR *********** " + e.getCause());
            throw e;
        }

        return recaptchaResponse;
    }
}
