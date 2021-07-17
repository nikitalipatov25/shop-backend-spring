package com.nikitalipatov.handmadeshop.supportingClasses;

public class CartInfo {

    private String productsInfo;
    private int totalItems;
    private double totalCost;

    public CartInfo() {
    }

    public CartInfo(String productsInfo, int totalItems, double totalCost) {
        this.productsInfo = productsInfo;
        this.totalItems = totalItems;
        this.totalCost = totalCost;
    }

    public String getProductsInfo() {
        return productsInfo;
    }

    public void setProductsInfo(String productsInfo) {
        this.productsInfo = productsInfo;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
