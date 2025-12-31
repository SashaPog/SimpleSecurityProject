package demoapp.newjwtspringsecurity.rabbitmq.controller;

import demoapp.newjwtspringsecurity.rabbitmq.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/broker")
@RequiredArgsConstructor
public class MainController {
    private final MessageSender messageSender;

    @PostMapping("/send")
    public ResponseEntity<?> send (@RequestBody String text) {
        if (text.isBlank()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        messageSender.send(text);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/send-e")
    public ResponseEntity<?> sendExchange (@RequestBody String text) {
        if (text.isBlank()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        messageSender.sendExchange(text);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }


}
