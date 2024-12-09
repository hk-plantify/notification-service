package com.plantify.notification.service;

import com.plantify.notification.domain.dto.NotificationResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

    SseEmitter subscribe();
    void sendToClient(NotificationResponse response);
}
