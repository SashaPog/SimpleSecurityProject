package demoapp.newjwtspringsecurity.rabbitmq.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueConfig {
    private final RabbitAdmin rabbitAdmin;
    private final Queue queue;

    @PostConstruct
    public void declareQueue() {
        rabbitAdmin.declareQueue(queue);
    }
}
