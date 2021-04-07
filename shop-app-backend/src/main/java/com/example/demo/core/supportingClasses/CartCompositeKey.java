package com.example.demo.core.supportingClasses;

import java.io.Serializable;
import java.util.UUID;

public class CartCompositeKey implements Serializable {

    private UUID productId;
    private UUID userId;

    public CartCompositeKey() {}

    public CartCompositeKey(UUID productId, UUID userId) {
        this.productId = productId;
        this.userId = userId;
    }

}
