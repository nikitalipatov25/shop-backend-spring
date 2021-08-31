package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.helpers.CartCompositeKey;
import com.nikitalipatov.handmadeshop.helpers.ProductUserCompositeKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "carts")
@IdClass(CartCompositeKey.class)
@Getter
@Setter
public class NewCart {
    @Id
    private UUID productId;
    @Id
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    private int amount;
}
