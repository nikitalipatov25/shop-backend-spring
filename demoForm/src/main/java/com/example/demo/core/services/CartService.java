package com.example.demo.core.services;

import com.example.demo.core.models.CartEntity;
import com.example.demo.core.models.CatalogEntity;
import com.example.demo.core.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CatalogService catalogService) {
        this.cartRepository = cartRepository;
    }


    public CartEntity createCart(CartEntity cartEntity) {
        CartEntity newCartEntity = new CartEntity();
        newCartEntity.setId(UUID.randomUUID());
        newCartEntity.setCatalogProductName(cartEntity.getCatalogProductName());
        newCartEntity.setCatalogProductPrice(cartEntity.getCatalogProductPrice());
        newCartEntity.setProductCost(cartEntity.getProductCost());
        newCartEntity.setProductDiscount(cartEntity.getProductDiscount());
        newCartEntity.setProductFinalPrice(cartEntity.getProductFinalPrice());
        newCartEntity.setSelectedProductKol(cartEntity.getSelectedProductKol());
        return cartRepository.save(newCartEntity);
    }

    public Page<CartEntity> getAllCart(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    public Optional<CartEntity> getById(UUID id) {
        return cartRepository.findById(id);
    }

    public Optional<Boolean> deleteCartItem(UUID id) {
        Optional<CartEntity> deletedItem = cartRepository.findById(id);
        return deletedItem
                .map(cartEntity -> {
                    cartRepository.deleteById(id);
                    return true;
                });
    }
}
