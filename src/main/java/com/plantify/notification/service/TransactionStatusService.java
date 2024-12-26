package com.plantify.notification.service;

import com.plantify.notification.domain.dto.TransactionStatusMessage;

public interface TransactionStatusService {

    void processSuccessfulTransaction(TransactionStatusMessage message);
    void processFailedTransaction(TransactionStatusMessage message);
}
