package demoapp.newjwtspringsecurity.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSender {
    private final AmqpTemplate amqpTemplate;
    private final RabbitTemplate rabbitTemplate;
    @Value("${queue.name}")
    private String queueName;

    public void send(String message){
        amqpTemplate.convertAndSend(queueName, message);
    }

    public void sendExchange (String message) {
        rabbitTemplate.convertAndSend("fourth.exchange", "", message);
    }
}
