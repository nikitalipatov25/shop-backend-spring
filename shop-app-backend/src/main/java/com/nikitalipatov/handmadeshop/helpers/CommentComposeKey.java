package com.nikitalipatov.handmadeshop.helpers;

import java.io.Serializable;
import java.util.UUID;

public class CommentComposeKey implements Serializable {
    private Long commentId;
    private UUID productId;

    public CommentComposeKey() {}

    public CommentComposeKey(Long commentId, UUID productId) {
        this.commentId = commentId;
        this.productId = productId;
    }

}
