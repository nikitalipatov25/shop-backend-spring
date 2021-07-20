package com.nikitalipatov.handmadeshop.supportingClasses;

import java.io.Serializable;
import java.util.UUID;
            //CartComposeKey
public class ProductUserCompositeKey implements Serializable {

    private UUID productId;
    private String userName;

    public ProductUserCompositeKey() {}

    public ProductUserCompositeKey(UUID productId, String userName) {
        this.productId = productId;
        this.userName = userName;
    }

}
