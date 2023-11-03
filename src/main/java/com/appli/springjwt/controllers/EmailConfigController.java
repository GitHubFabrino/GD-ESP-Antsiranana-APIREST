package com.appli.springjwt.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/email-config")
public class EmailConfigController {

    private final Properties emailProperties;

    public EmailConfigController(@Value("${spring.mail.username}") String emailUsername,
                                 @Value("${spring.mail.password}") String emailPassword) {
        emailProperties = new Properties();
        emailProperties.put("spring.mail.username", emailUsername);
        emailProperties.put("spring.mail.password", emailPassword);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEmailConfig(@RequestParam("username") String username,
                                               @RequestParam("password") String password) {
        System.out.println("EmailConfigController : updateEmailConfig");

        emailProperties.setProperty("spring.mail.username", username);
        emailProperties.setProperty("spring.mail.password", password);

        return ResponseEntity.ok().build();
    }
}
