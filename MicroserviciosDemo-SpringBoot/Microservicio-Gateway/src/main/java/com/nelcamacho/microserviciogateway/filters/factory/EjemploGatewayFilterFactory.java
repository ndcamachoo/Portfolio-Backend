package com.nelcamacho.microserviciogateway.filters.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configurations> {

    // Esta clase extiende de AbstractGatewayFilterFactory<Configurations> la cual es una interfaz que contiene los métodos
    // para configurar el filtro de manera personalizada utilizando la Abstract Gateway Filter Factory.

    /* =============== Atributos =============== */

    private static final Logger log = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

    /* =============== Métodos =============== */

    @Override
    public GatewayFilter apply(Configurations config) {

        // Este método es el que se encarga de crear el filtro.
        return new OrderedGatewayFilter((exchange, chain) -> {
            // (PRE)
            log.info(("GatewayFilterFactory: PRE -> Mensaje: " + config.msg)); // Se imprime el mensaje en el log para verificar que se está ejecutando el filtro (PRE)

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // (POST)

                        Optional.ofNullable(config.cookieValue).ifPresent(c -> {
                            exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, c).build()); // Se agrega el cookie al response utilizando el método POST del filtro.
                        });

                        log.info(("GatewayFilterFactory: POST -> Mensaje: " + config.msg)); // Se imprime el mensaje en el log para verificar que se está ejecutando el filtro (POST)

                    }));
        }, 2); // Se establece el orden del filtro. Solamente si se requiere un orden especifico, es Opcional.

    }

    // shortcutFieldOrder es una lista de campos que se utilizan para configurar el filtro.
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("msg", "cookieName", "cookieValue"); // Establece los parametros de entrada del filtro en el caso de ingresar la configuración en una sola línea.
    }

    // Name es el nombre del filtro que se utiliza en el archivo de configuración.
    @Override
    public String name() {
        return "Ejemplo"; // Nombre del filtro.
    }

    /* =============== Constructor =============== */

    public EjemploGatewayFilterFactory() {
        // Este constructor es el que se encarga de configurar el filtro.
        super(Configurations.class);
    }

    /* =============== Clases ================ */

    public static class Configurations {
        // Esta clase contiene las configuraciones del filtro.

        /* ================== Atributos =============== */

        private String msg;
        private String cookieValue;
        private String cookieName;

        /* ================== Getters y Setters =============== */

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

        /* ================== Constructor =============== */

        public Configurations() {
        }
    }

}
