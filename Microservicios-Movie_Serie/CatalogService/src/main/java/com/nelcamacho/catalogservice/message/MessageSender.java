package com.nelcamacho.catalogservice.message;

import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    /* ===================== Attributes ===================== */

    /*private final RabbitTemplate rabbitTemplate;
    private final Queue catalogQueue;
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);*/

    /* ===================== Methods ===================== */

    /*public void send(Catalog catalog) {
        log.info("[SEND MESSAGE TO " + this.catalogQueue.getName() + "] -> " + catalog);
        this.rabbitTemplate.convertAndSend(this.catalogQueue.getName(), catalog);
    }*/

    /* ===================== Constructors ===================== */

    /*@Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, Queue catalogQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.catalogQueue = catalogQueue;
    }*/

}
