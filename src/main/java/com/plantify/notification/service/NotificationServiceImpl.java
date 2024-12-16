package com.plantify.notification.service;

import com.plantify.notification.domain.dto.NotificationResponse;
import com.plantify.notification.global.util.UserInfoProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

private final Sinks.Many<NotificationResponse> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final UserInfoProvider userInfoProvider;

    public Flux<NotificationResponse> subscribe() {
//        Long userId = userInfoProvider.getUserInfo().userId();
        Long userId = 2L;
        log.info("Subscribing userId: {}", userId);
        return sink.asFlux()
                .filter(response -> response.userId().equals(userId))
                .doOnSubscribe(subscription -> log.info("User {} subscribed.", userId));
    }

    public void sendToClient(NotificationResponse response) {
        log.info("Sending notification to userId: {}", response.userId());
        sink.tryEmitNext(response);
    }
}
