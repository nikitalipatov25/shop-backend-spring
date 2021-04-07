package com.example.demo.core.supportingClasses;

import com.example.demo.core.models.CartEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class CartDTO {

    private Page<CartEntity> catalogPage;
    private List<?> cartSummary;

    public Page<CartEntity> getCatalogPage() {
        return catalogPage;
    }

    public void setCatalogPage(Page<CartEntity> catalogPage) {
        this.catalogPage = catalogPage;
    }

    public List<?> getCartSummary() {
        return cartSummary;
    }

    public void setCartSummary(List<?> cartSummary) {
        this.cartSummary = cartSummary;
    }
}
