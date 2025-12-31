package demoapp.newjwtspringsecurity.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitReciever {

    @RabbitListener(queues = {"FourthQueue"})
    public void receive(String message) {
        log.info(message + " - received from queue");
    }

    @RabbitListener(queues = "FourthQueue.A")
    public void receiveA(String message) {
        log.info("A received: {}", message);
    }

    @RabbitListener(queues = "FourthQueue.B")
    public void receiveB(String message) {
        log.info("B received: {}", message);
    }
}
