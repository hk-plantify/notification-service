package com.plantify.notification.service;

import com.plantify.notification.domain.dto.NotificationResponse;
import com.plantify.notification.domain.dto.TransactionStatusMessage;
import com.plantify.notification.domain.entity.Notification;
import com.plantify.notification.domain.entity.Status;
import com.plantify.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionStatusServiceImpl implements TransactionStatusService {

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    @Override
    public void processSuccessfulTransaction(TransactionStatusMessage message) {
        try {
            Notification notification = Notification.builder()
                    .userId(message.userId())
                    .status(message.status())
                    .build();
            Notification savedNotification = notificationRepository.save(notification);

            NotificationResponse response = NotificationResponse.from(savedNotification, message);
            notificationService.sendToClient(response);
        } catch (Exception e) {
            log.error("Error handling transaction status message: {}", e.getMessage());
        }
    }

    @Override
    public void processFailedTransaction(TransactionStatusMessage message) {
        try {
            Notification notification = Notification.builder()
                    .userId(message.userId())
                    .status(Status.FAILED)
                    .build();
            Notification savedNotification = notificationRepository.save(notification);

            NotificationResponse response = NotificationResponse.from(savedNotification, message);
            notificationService.sendToClient(response);
        } catch (Exception e) {
            log.error("Error handling failed transaction: {}", e.getMessage());
        }
    }
}
