package com.plantify.notification.domain.dto;

import com.plantify.notification.domain.entity.Status;

public record TransactionStatusMessage(
        Long transactionId,
        Long userId,
        String orderId,
        Long amount,
        Status status
){}