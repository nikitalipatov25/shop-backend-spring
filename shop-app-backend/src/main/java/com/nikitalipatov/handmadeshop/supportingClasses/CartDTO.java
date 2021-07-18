package com.nikitalipatov.handmadeshop.supportingClasses;

import com.nikitalipatov.handmadeshop.core.models.Cart;
import org.springframework.data.domain.Page;

import java.util.List;

public class CartDTO {

    private Page<Cart> catalogPage;
    private List<?> cartSummary;

    public Page<Cart> getCatalogPage() {
        return catalogPage;
    }

    public void setCatalogPage(Page<Cart> catalogPage) {
        this.catalogPage = catalogPage;
    }

    public List<?> getCartSummary() {
        return cartSummary;
    }

    public void setCartSummary(List<?> cartSummary) {
        this.cartSummary = cartSummary;
    }
}
