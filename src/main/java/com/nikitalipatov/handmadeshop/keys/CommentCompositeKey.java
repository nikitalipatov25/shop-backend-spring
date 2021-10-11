package com.nikitalipatov.handmadeshop.keys;

import java.io.Serializable;
import java.util.UUID;

public class CommentCompositeKey implements Serializable {
    private UUID productId;
    private Long userId;

    public CommentCompositeKey() {
    }

    public CommentCompositeKey(UUID productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }
}
