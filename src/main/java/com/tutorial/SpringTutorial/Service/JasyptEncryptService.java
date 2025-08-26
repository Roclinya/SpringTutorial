package com.tutorial.SpringTutorial.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JasyptEncryptService {

    @Value("${spring.mail.pcode:}")
    private String password;

    @PostConstruct
    public void init() {
        System.out.println("Decrypted password: " + password);
    }
}
