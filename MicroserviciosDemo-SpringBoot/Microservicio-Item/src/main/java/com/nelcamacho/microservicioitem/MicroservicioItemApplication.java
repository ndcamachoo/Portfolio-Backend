package com.nelcamacho.microservicioitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Esto habilita el cliente de feign e incluirlo en cualquier clase que lo requiera
@EnableEurekaClient // Habilita la aplicación como cliente de eureka
//@RibbonClient(name = "servicio-productos") // Esto habilita el cliente de ribbon y vincula el microservicio con el servicio con el nombre y establece el balanceo en función de las propiedades definidas.
//@EnableCircuitBreaker // (DEPRECADO) Habilita el circuit breaker para el microservicio utilizando Hystrix (Envuelve las llamadas a los servicios Ribbon (Eureka))
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class}) // Esto evita que se carguen las configuraciones de la base de datos
public class MicroservicioItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicioItemApplication.class, args);
    }

}
