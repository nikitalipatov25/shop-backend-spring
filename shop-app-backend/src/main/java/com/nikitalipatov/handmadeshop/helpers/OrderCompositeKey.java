package com.nikitalipatov.handmadeshop.helpers;

import java.io.Serializable;
import java.util.UUID;

public class OrderCompositeKey implements Serializable {
    private UUID orderId;
    private Long userId;

    public OrderCompositeKey() {
    }

    public OrderCompositeKey(UUID orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}
