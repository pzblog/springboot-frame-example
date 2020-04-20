package com.example.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiver3 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("fanout.C: " + hello);
    }

}
