package com.plantify.notification.domain.dto;

import com.plantify.notification.domain.entity.Notification;
import com.plantify.notification.domain.entity.Status;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long userId,
        Long transactionId,
        Status status,
        Long amount,
        LocalDateTime createdAt
) {
    public static NotificationResponse from(Notification notification, TransactionStatusMessage transactionMessage) {
        return new NotificationResponse(
                notification.getUserId(),
                transactionMessage.transactionId(),
                transactionMessage.status(),
                transactionMessage.amount(),
                notification.getCreatedAt()
        );
    }
}