package com.nelcamacho.movieservice.message;

import com.nelcamacho.movieservice.models.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {

    /* =========================== Methods =========================== */

    /* @RabbitListener(queues = "${queue.movie.name}")
    public void receiveMessage(@Payload Movie movie) {
        System.out.println(movie);
    }*/
}
