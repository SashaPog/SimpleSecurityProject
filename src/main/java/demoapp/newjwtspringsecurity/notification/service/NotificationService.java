package demoapp.newjwtspringsecurity.notification.service;

import demoapp.newjwtspringsecurity.notification.dto.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    public void send (NotificationRequestDto notificationRequest) {
        System.out.println(notificationRequest.getMessage());
    }
}
