package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.keys.CartCompositeKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
    private int amount;
}
