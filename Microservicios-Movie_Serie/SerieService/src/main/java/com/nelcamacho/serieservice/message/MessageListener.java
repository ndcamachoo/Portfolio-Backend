package com.nelcamacho.serieservice.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    /* =========================== Methods =========================== */

    /* @RabbitListener(queues = "${queue.serie.name}")
    public void receiveMessage(@Payload Serie serie) {
        System.out.println(movie);
    }*/
}
