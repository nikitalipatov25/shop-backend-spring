package com.nikitalipatov.handmadeshop.supportingClasses;

import com.nikitalipatov.handmadeshop.core.models.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartAnalyzer {

    private int itemsInCart;
    private double cartPrice;
    private double cartDiscount;
    private double finalResult;
    ArrayList<Object> calculationResultList;

    public int getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(int itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    public double getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(double cartPrice) {
        this.cartPrice = cartPrice;
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

    public ArrayList<Object> getCalculationResultList() {
        return calculationResultList;
    }

    public void setCalculationResultList(ArrayList<Object> calculationResultList) {
        this.calculationResultList = calculationResultList;
    }

    public ArrayList<Object> resultOfCalculation(List<Cart> cart) {
        calculationResultList = new ArrayList<>();
        calculationResultList.add(calculateItemsInCart(cart));
        calculationResultList.add(Math.round(calculateCartPrice(cart) * 100.0) / 100.0);
        calculationResultList.add(Math.round(calculateCartDiscount() * 100.0) / 100.0);
        calculationResultList.add(Math.round(calculateFinalResult() * 100.0) / 100.0);
        return calculationResultList;
    }

    public double calculateItemCost(double catalogProductPrice, int selectedProductKol) {
        return catalogProductPrice * selectedProductKol;
    }

    public int calculateItemsInCart(List<Cart> cart) {
        int value = 0;
        for (int i = 0; i < cart.size(); i++) {
            value = value + cart.get(i).getSelectedProductKol();
        }
        setItemsInCart(value);
        return getItemsInCart();
    }

    public double calculateCartPrice(List<Cart> cart) {
        double value = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            value = value + cart.get(i).getProductCost();
        }
        setCartPrice(value);
        return getCartPrice();
    }

    public double calculateCartDiscount() {
        double value = 0.0;
        if (getItemsInCart() >= 10) {
            value = getCartPrice() * 0.05;
            setCartDiscount(value);
        } else {
            setCartDiscount(value);
        }
    return getCartDiscount();
    }

    public double calculateFinalResult() {
        setFinalResult(getCartPrice() - getCartDiscount());
        return getFinalResult();
    }
}
