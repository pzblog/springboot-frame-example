package com.example.rabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送消息
 */
@Component
public class HelloSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "hello " + new Date();
            System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }

    public void send(int var){
        String context = "Spring boot hello queue " + var;
//        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }

}
