package demoapp.newjwtspringsecurity.rabbitmq.service;

import demoapp.newjwtspringsecurity.rabbitmq.config.RabbitMQTopicConfig;
import demoapp.newjwtspringsecurity.rabbitmq.dto.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQTopicConfig.QUEUE)
    public void consume(UserMessage msg) {
        log.info("📩 Received message: {}", msg);
    }
}
