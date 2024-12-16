package com.plantify.notification.service;

import com.plantify.notification.domain.dto.NotificationResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

public interface NotificationService {

    Flux<NotificationResponse> subscribe();
    void sendToClient(NotificationResponse response);
}
