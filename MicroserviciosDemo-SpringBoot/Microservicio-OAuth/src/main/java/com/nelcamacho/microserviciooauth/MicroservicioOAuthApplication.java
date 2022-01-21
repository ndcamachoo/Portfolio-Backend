package com.nelcamacho.microserviciooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EntityScan({"com.nelcamacho.microserviciousuarioscommons.models"})
public class MicroservicioOAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioOAuthApplication.class, args);
    }

}
