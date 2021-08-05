package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.helpers.OrdersCompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "orders")
@IdClass(OrdersCompositeKey.class)
public class Orders {

    @Id
    private UUID orderId;

    @Id
    private Long userId;

    private String productsInfo;

    private double finalPrice;

    private String orderStatus;

    private String orderType;

    private String orderDate;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductsInfo() {
        return productsInfo;
    }

    public void setProductsInfo(String productsInfo) {
        this.productsInfo = productsInfo;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
