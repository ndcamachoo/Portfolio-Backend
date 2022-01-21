package com.nelcamacho.microservicioconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroservicioConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioConfigServerApplication.class, args);
    }

}
