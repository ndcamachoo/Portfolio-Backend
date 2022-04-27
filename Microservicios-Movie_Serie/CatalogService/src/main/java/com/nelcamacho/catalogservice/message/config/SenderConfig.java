package com.nelcamacho.serieservice.message.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    /* ======================= Attributes ======================= */
    @Value ("${queue.catalog.name}")
    private String queueName;

    /* ======================== Methods ======================== */
    @Bean
    public Queue queue() {
        return new Queue(this.queueName, true);
    }

}
