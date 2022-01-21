package com.nelcamacho.microservicioproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient // Establece la aplicación como cliente de eureka
@EntityScan({"com.nelcamacho.microserviciocommons.models"}) // Establece la clase de la que se escanean las entidades
public class MicroservicioProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioProductosApplication.class, args);
    }

}
