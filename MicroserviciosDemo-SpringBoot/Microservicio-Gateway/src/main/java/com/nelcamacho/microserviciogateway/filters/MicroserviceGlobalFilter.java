package com.nelcamacho.microserviciogateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class MicroserviceGlobalFilter implements GlobalFilter, Ordered {

    // Se implementa GlobalFilter para establecer una clase que actua como filtro global para todos los microservicios conectados al gateway.
    // Se ejecuta en todas las peticiones que llegan al gateway.

    // Se implementa Ordered para establecer un orden de ejecución de los filtros.

    /* ==================== Atributos ==================== */

    private static final Logger log = LoggerFactory.getLogger(MicroserviceGlobalFilter.class);  // Se crea un objeto de tipo Logger para registrar los mensajes de log.

    /* ==================== Métodos ==================== */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // Exchange: objeto que contiene toda la información de la petición y respuesta.
        // Chain: objeto que contiene todos los filtros que se van a ejecutar en el flujo de la petición.

        // Pre: Se ejecuta antes de que se ejecute el filtro. (Antes de que se retorne el resultado de la petición).
        //log.info("Pre-personal filter");
        //exchange.getRequest().mutate().headers(h -> h.add("token", "12345"));  // Se agrega una cabecera al request. Se agrega un token para que el microservicio de autenticación pueda validar la petición.
        // Nombre del microservicio en el log
        log.info("Ruta del microservicio: " + exchange.getRequest().getPath().value());

        return chain.filter(exchange)  // Se ejecuta el filtro. Continúa con el flujo de la petición (PRE).
                .then(Mono.fromRunnable(() -> { // Se ejecuta en función de la respuesta del filtro.
                    //(POST)
                    //log.info("Post-personal filter");

                    //Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(v -> {
                    //    exchange.getResponse().getHeaders().add("token", v);
                    //});  // Se obtiene la cabecera del request y se agrega una cabecera al response. En este caso se agrega el token que se obtuvo en el request.

                    //.getResponse().getCookies().add("color", ResponseCookie.from("color","rojo").build()); // Se establece una cookie en la respuesta.
                    //exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON); // Se establece el tipo de contenido de la respuesta.
                }));
    }

    @Override
    public int getOrder() {
        return 1; // Se establece el orden de ejecución del filtro. Se establece un valor negativo para que se ejecute antes que los filtros de los microservicios. (Orden de ejecución: -1)
        // Da un error con alta prioridad y no se pueden guardar datos en la cabecera si se utilizan filtros personalizados.
    }
}
