package com.plantify.notification.domain.entity;

import java.io.Serializable;

public enum TransactionType implements Serializable {
    PAYMENT,
    REFUND,
    CANCELLATION,
    POINT_REWARD,
    POINT_REDEEM
}