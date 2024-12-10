package com.plantify.notification.domain.dto;

import com.plantify.notification.domain.entity.Status;
import com.plantify.notification.domain.entity.TransactionType;

public record TransactionStatusMessage(
        Long transactionId,
        Long userId,
        Long orderId,
        Long amount,
        TransactionType transactionType,
        Status status
) {}
