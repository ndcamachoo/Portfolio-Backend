package com.nelcamacho.microservicioitem.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfiguration {

    // @Configuration
    // Nos permite crear objetos (componentes) de Spring, como beans, que se pueden usar en cualquier parte del código de nuestra aplicación
    // Se pueden registrar en el contexto de Spring, y pueden ser usados en cualquier parte de nuestra aplicación mediante el @Autowired
    // @Bean
    // Nos permite crear un bean de Spring, que se puede usar en cualquier parte de nuestra aplicación basado en un método Singleton

    // RestTemplate
    // Es una librería que nos permite hacer peticiones REST a servicios REST de terceros

    @Bean // Se puede definir el nombre del bean que se va a crear utilizando ("nombre")
    //@LoadBalanced // Si se utiliza esta anotación, Spring Cloud Ribbon se encargará de seleccionar una instancia de servicio de acuerdo a la politica de balanceo mediante RestTemplate
    public RestTemplate registrarRestTemplate() {
        return new RestTemplate();
    }

    // Se aprovecha esta clase, ya que posee la anotación @Configuration, pero para manejar la configuración de Resilience4j, se puede crear una clase aparte
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(10) // Cantidad de solicitudes que se van a contar para determinar si el circuito está abierto o cerrado
                        .failureRateThreshold(50) // Porcentaje de fallos que se deben contar para abrir el circuito
                        .waitDurationInOpenState(Duration.ofSeconds(10L)) // Duración de tiempo que se espera para cerrar el circuito
                        .permittedNumberOfCallsInHalfOpenState(5) // Cantidad de llamadas que se permiten en el estado de apertura del circuito -> Semi-abierto
                        .slowCallRateThreshold(50) // Porcentaje de solicitudes que se deben contar para considerar una solicitud lenta
                        .slowCallDurationThreshold(Duration.ofSeconds(2L)) // Duración de tiempo que se espera para considerar una solicitud lenta
                        .build()) // Se crea el objeto CircuitBreakerConfig
                //.timeLimiterConfig(TimeLimiterConfig.ofDefaults()) // Se establece el tiempo límite de la solicitud (en caso de que se haya configurado un tiempo limite) -> Default (Por defecto es de 1 segundo de Timeout)
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(3L)) // Tiempo límite de la solicitud
                        .build()
                )
        .build());
    }
}
