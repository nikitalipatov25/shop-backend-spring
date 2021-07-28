package com.nikitalipatov.handmadeshop.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
public class SaleReport {

    @Id
    private UUID productId;

    @Column(name = "sold")
    private int quantitySold;

    private double price;

    @Column(name = "last_sale")
    private String dateLastSale;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateLastSale() {
        return dateLastSale;
    }

    public void setDateLastSale(String dateLastSale) {
        this.dateLastSale = dateLastSale;
    }
}
