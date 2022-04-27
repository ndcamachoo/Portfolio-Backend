package com.nelcamacho.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CountRequestsFilter extends AbstractGatewayFilterFactory<CountRequestsFilter.Configurations> {

    /* =============== Atributos =============== */

    private static final Logger log = LoggerFactory.getLogger(CountRequestsFilter.class);
    private static final AtomicInteger COUNT_CALL_GATEWAY = new AtomicInteger(0);

    /* =============== Métodos =============== */

    @Override
    public GatewayFilter apply(CountRequestsFilter.Configurations config) {
        return (exchange, chain) -> {
            // (PRE)
            log.info("[COUNT CALLS GATEWAY] -> {}", COUNT_CALL_GATEWAY.incrementAndGet());

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        // (POST)
                    }));
        };
    }

    public CountRequestsFilter() {
        super(CountRequestsFilter.Configurations.class);
    }

    /* =============== Clases ================ */

    public static class Configurations {
    }


}
