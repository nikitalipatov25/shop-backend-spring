package com.example.demo.core.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class CatalogEntity {

    @Id
    private UUID id;

    private String productName;

    private String productDescription;

    private double productPrice;

    private String productPhoto;

    private int productKol;

    private String categoryType; // кошки, собаки..

    private String categoryName; // игрушки, лежанки, аксессуары..

    /*
    *
    * Добавить две новых поля в сервис
    *
    * */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public int getProductKol() {
        return productKol;
    }

    public void setProductKol(int productKol) {
        this.productKol = productKol;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
