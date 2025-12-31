package demoapp.newjwtspringsecurity.rabbitmq.controller;

import demoapp.newjwtspringsecurity.rabbitmq.dto.UserMessage;
import demoapp.newjwtspringsecurity.rabbitmq.service.RabbitMQMessageProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@AllArgsConstructor
public class MessageController {

    private final RabbitMQMessageProducer messageProducer;

    @PostMapping
    public ResponseEntity<String> send(@RequestBody UserMessage msg) {
        messageProducer.publish(
            msg,
            "internal.exchange",
            "internal.routing-key"
        );
        return ResponseEntity.ok("Message sent");
    }
}