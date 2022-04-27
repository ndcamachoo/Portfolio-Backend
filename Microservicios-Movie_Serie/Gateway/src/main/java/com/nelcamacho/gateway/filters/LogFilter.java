package com.nelcamacho.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Calendar;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Configurations> {

    /* =============== Atributos =============== */

    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

    /* =============== Métodos =============== */

    @Override
    public GatewayFilter apply(Configurations config) {
        return (exchange, chain) -> {
            // (PRE)
            log.info("[PATH REQUESTED] -> {}",exchange.getRequest().getPath());

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // (POST)
                        log.info("[TIME RESPONSE] -> {}", Calendar.getInstance().getTime());
                    }));
        };
    }

    public LogFilter() {
        super(LogFilter.Configurations.class);
    }

    /* =============== Clases ================ */

    public static class Configurations {
    }
}
