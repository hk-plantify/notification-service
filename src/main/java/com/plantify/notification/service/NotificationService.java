package com.plantify.notification.service;

import com.plantify.notification.domain.dto.NotificationResponse;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

public interface NotificationService {

//    SseEmitter subscribe();
//    void sendToClient(NotificationResponse response);
    Flux<NotificationResponse> subscribe();
    void sendToClient(NotificationResponse response);
}
