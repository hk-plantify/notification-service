package com.plantify.notification.service;

import com.plantify.notification.domain.dto.NotificationResponse;
import com.plantify.notification.global.util.UserInfoProvider;
import com.plantify.notification.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

//    private final EmitterRepository emitterRepository;
//    private final UserInfoProvider userInfoProvider;
//    private static final Long DEFAULT_TIMEOUT = 600L * 1000 * 60;
//
//    public SseEmitter subscribe() {
//        Long userId = userInfoProvider.getUserInfo().userId();
//        SseEmitter emitter = createEmitter(userId);
//        log.info("Subscribing to emitter {}", emitter);
//        return emitter;
//    }
//
//    public void sendToClient(NotificationResponse response) {
//        SseEmitter emitter = emitterRepository.get(response.userId());
//        if (emitter != null) {
//            try {
//                emitter.send(SseEmitter.event()
//                        .name("notification")
//                        .data(response));
//            } catch (IOException e) {
//                emitterRepository.deleteById(response.userId());
//                emitter.completeWithError(e);
//            }
//        }
//    }
//
//    private SseEmitter createEmitter(Long userId) {
//        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
//        emitterRepository.save(userId, emitter);
//
//        emitter.onCompletion(() -> emitterRepository.deleteById(userId));
//        emitter.onTimeout(() -> emitterRepository.deleteById(userId));
//        return emitter;
//    }
private final Sinks.Many<NotificationResponse> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final UserInfoProvider userInfoProvider;

    public Flux<NotificationResponse> subscribe() {
        Long userId = userInfoProvider.getUserInfo().userId();
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
