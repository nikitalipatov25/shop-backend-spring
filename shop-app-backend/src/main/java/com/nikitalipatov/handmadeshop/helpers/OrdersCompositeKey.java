package com.nikitalipatov.handmadeshop.helpers;

import java.io.Serializable;
import java.util.UUID;

public class OrdersCompositeKey implements Serializable {

    private UUID orderId;
    private Long userId;

    public OrdersCompositeKey() {}

    public OrdersCompositeKey(UUID orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}
