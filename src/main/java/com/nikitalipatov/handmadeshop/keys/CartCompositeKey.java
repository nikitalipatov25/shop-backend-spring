package com.nikitalipatov.handmadeshop.keys;

import java.io.Serializable;
import java.util.UUID;

public class CartCompositeKey implements Serializable {
    private UUID productId;
    private Long userId;

    public CartCompositeKey() {
    }

    public CartCompositeKey(UUID productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }
}
