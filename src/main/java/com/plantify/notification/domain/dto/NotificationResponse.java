package com.plantify.notification.domain.dto;

import com.plantify.notification.domain.entity.Notification;
import com.plantify.notification.domain.entity.Status;
import com.plantify.notification.domain.entity.TransactionType;

import java.time.LocalDateTime;

public record NotificationResponse(
        Long notificationId,
        Long userId,
        Long transactionId,
        TransactionType transactionType,
        Status status,
        Long amount,
        LocalDateTime createdAt
) {
    public static NotificationResponse from(Notification notification, TransactionStatusMessage transactionMessage) {
        return new NotificationResponse(
                notification.getNotificationId(),
                notification.getUserId(),
                transactionMessage.transactionId(),
                transactionMessage.transactionType(),
                transactionMessage.status(),
                transactionMessage.amount(),
                notification.getCreatedAt()
        );
    }
}