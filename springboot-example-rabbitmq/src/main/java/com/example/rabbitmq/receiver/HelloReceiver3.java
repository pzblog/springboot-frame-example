package com.example.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class HelloReceiver3 {

    @RabbitHandler
    public void process(String hello) {

        System.out.println("topic.message: " + hello);
    }

}
