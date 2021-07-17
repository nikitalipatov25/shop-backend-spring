package com.nikitalipatov.handmadeshop.supportingClasses;

import java.io.Serializable;
import java.util.UUID;

public class CartCompositeKey implements Serializable {

    private UUID productId;
    private String userName;

    public CartCompositeKey() {}

    public CartCompositeKey(UUID productId, String userName) {
        this.productId = productId;
        this.userName = userName;
    }

}
