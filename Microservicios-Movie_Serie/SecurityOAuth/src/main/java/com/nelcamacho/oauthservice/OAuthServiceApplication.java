package com.nelcamacho.oauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthServiceApplication.class, args);
    }

}
