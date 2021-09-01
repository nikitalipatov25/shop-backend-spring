package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.helpers.OrderCompositeKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@IdClass(OrderCompositeKey.class)
@Getter
@Setter
public class NewOrder {
    @Id
    private UUID orderId;
    @Id
    private Long userId;
    @ElementCollection
    @CollectionTable(name = "orders_products_info")
    private List<String> productsInfo;
    private double summary;
    private Date date;
    private String orderType;
    private String orderStatus;
    private String extraInformation;
}
