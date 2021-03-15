package com.example.demo;

import com.example.demo.core.models.CartEntity;
import org.springframework.data.domain.Page;

public class CartAnalyzer {

    private int finalCartKol;
    private double finalCartPrice;
    private double cartDiscount;
    private double finalResult;

    public int getFinalCartKol() {
        return finalCartKol;
    }

    public void setFinalCartKol(int finalCartKol) {
        this.finalCartKol = finalCartKol;
    }

    public double getFinalCartPrice() {
        return finalCartPrice;
    }

    public void setFinalCartPrice(double finalCartPrice) {
        this.finalCartPrice = finalCartPrice;
    }

    public double getCartDiscount() {
        return cartDiscount;
    }

    public void setCartDiscount(double cartDiscount) {
        this.cartDiscount = cartDiscount;
    }

    public double getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(double finalResult) {
        this.finalResult = finalResult;
    }

    public double calculateCartItemProductCost(double catalogProductPrice, int selectedProductKol) {
        return catalogProductPrice * selectedProductKol;
    }

    public void calculateFinalCartKol(Page<CartEntity> all) {
        int value = 0;
        for (int i = 0; i < all.getNumberOfElements(); i++) {
            value = value + all.getContent().get(i).getSelectedProductKol();
        }
        setFinalCartKol(value);
        System.out.println("Всего товаров в корзине: " + getFinalCartKol());
    }

    public void calculateFinalCartPrice(Page<CartEntity> all) {
        double value = 0.0;
        for (int i = 0; i < all.getNumberOfElements(); i++) {
            value = value + all.getContent().get(i).getProductCost();
        }
        setFinalCartPrice(value);
        System.out.println("Стоимость товаров в корзине: " + getFinalCartPrice());
        setFinalResult(value);
    }

    public void checkDiscount() {
        if (getFinalCartKol() >= 10) {
            double discount = getFinalCartPrice() * 0.05;
            setFinalResult(getFinalResult() - discount);
            System.out.println("Цена с учетом скидки: " + getFinalResult());
        }
    }
}
