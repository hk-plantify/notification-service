package com.plantify.notification.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    public void save(Long userId, SseEmitter emitter) {
        emitters.put(userId, emitter);
    }

    public void deleteById(Long userId) {
        emitters.remove(userId);
    }

    public SseEmitter get(Long userId) {
        return emitters.get(userId);
    }
}