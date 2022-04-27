package com.nelcamacho.catalogservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class BreakerConfiguration {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(10) // Cantidad de solicitudes que se van a contar para determinar si el circuito está abierto o cerrado
                        .failureRateThreshold(50) // Porcentaje de fallos que se deben contar para abrir el circuito
                        .waitDurationInOpenState(Duration.ofSeconds(10L)) // Duración de tiempo que se espera para cerrar el circuito
                        .permittedNumberOfCallsInHalfOpenState(5) // Cantidad de llamadas que se permiten en el estado de apertura del circuito -> Semi-abierto
                        .slowCallRateThreshold(50) // Porcentaje de solicitudes que se deben contar para considerar una solicitud lenta
                        .slowCallDurationThreshold(Duration.ofSeconds(5L)) // Duración de tiempo que se espera para considerar una solicitud lenta
                        .build()) // Se crea el objeto CircuitBreakerConfig
                //.timeLimiterConfig(TimeLimiterConfig.ofDefaults()) // Se establece el tiempo límite de la solicitud (en caso de que se haya configurado un tiempo limite) -> Default (Por defecto es de 1 segundo de Timeout)
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(3L)) // Tiempo límite de la solicitud
                        .build()
                )
                .build());
    }

}
