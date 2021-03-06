package com.example.demo.core.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class CartEntity {

    @Id
    private UUID id;

    private String catalogProductName;

    private String catalogProductPhoto;

    private int selectedProductKol;

    private double catalogProductPrice;

    private double productCost;

    private  double productDiscount;

    private double productFinalPrice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCatalogProductName() {
        return catalogProductName;
    }

    public void setCatalogProductName(String catalogProductName) {
        this.catalogProductName = catalogProductName;
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

    public double getProductFinalPrice() {
        return productFinalPrice;
    }

    public void setProductFinalPrice(double productFinalPrice) {
        this.productFinalPrice = productFinalPrice;
    }

    public String getCatalogProductPhoto() {
        return catalogProductPhoto;
    }

    public void setCatalogProductPhoto(String catalogProductPhoto) {
        this.catalogProductPhoto = catalogProductPhoto;
    }
}
