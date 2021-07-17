package com.nikitalipatov.handmadeshop.core.models;

import com.nikitalipatov.handmadeshop.supportingClasses.CartCompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@IdClass(CartCompositeKey.class)
public class CartEntity {

    @Id
    private UUID productId;

    @Id
    private String userName;

    private String catalogProductName;

    private String catalogProductPhoto;

    private int selectedProductKol;

    private double catalogProductPrice;

    private double productCost;

    private double productDiscount;

    private double finalSummary;

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCatalogProductName() {
        return catalogProductName;
    }

    public void setCatalogProductName(String catalogProductName) {
        this.catalogProductName = catalogProductName;
    }

    public String getCatalogProductPhoto() {
        return catalogProductPhoto;
    }

    public void setCatalogProductPhoto(String catalogProductPhoto) {
        this.catalogProductPhoto = catalogProductPhoto;
    }

    public int getSelectedProductKol() {
        return selectedProductKol;
    }

    public void setSelectedProductKol(int selectedProductKol) {
        this.selectedProductKol = selectedProductKol;
    }

    public double getCatalogProductPrice() {
        return catalogProductPrice;
    }

    public void setCatalogProductPrice(double catalogProductPrice) {
        this.catalogProductPrice = catalogProductPrice;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public double getFinalSummary() {
        return finalSummary;
    }

    public void setFinalSummary(double finalSummary) {
        this.finalSummary = finalSummary;
    }
}
