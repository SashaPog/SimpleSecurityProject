package demoapp.newjwtspringsecurity.notification.controller;

import demoapp.newjwtspringsecurity.notification.dto.NotificationRequestDto;
import demoapp.newjwtspringsecurity.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
@Slf4j
@Valid
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequestDto notificationRequestDto){
        log.info("new notification {}", notificationRequestDto);
        notificationService.send(notificationRequestDto);
    }
}
