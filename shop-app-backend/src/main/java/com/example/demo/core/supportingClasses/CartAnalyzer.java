package com.example.demo.core.supportingClasses;

import com.example.demo.core.models.CartEntity;

import java.text.DecimalFormat;
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

    public ArrayList<Object> resultOfCalculation(List<CartEntity> cartEntity) {
        calculationResultList = new ArrayList<>();
        calculationResultList.add(calculateItemsInCart(cartEntity));
        calculationResultList.add(Math.round(calculateCartPrice(cartEntity) * 100.0) / 100.0);
        calculationResultList.add(Math.round(calculateCartDiscount() * 100.0) / 100.0);
        calculationResultList.add(Math.round(calculateFinalResult() * 100.0) / 100.0);
        return calculationResultList;
    }

    public double calculateItemCost(double catalogProductPrice, int selectedProductKol) {
        return catalogProductPrice * selectedProductKol;
    }

    public int calculateItemsInCart(List<CartEntity> cartEntity) {
        int value = 0;
        for (int i = 0; i < cartEntity.size(); i++) {
            value = value + cartEntity.get(i).getSelectedProductKol();
        }
        setItemsInCart(value);
        return getItemsInCart();
    }

    public double calculateCartPrice(List<CartEntity> cartEntity) {
        double value = 0.0;
        for (int i = 0; i < cartEntity.size(); i++) {
            value = value + cartEntity.get(i).getProductCost();
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
