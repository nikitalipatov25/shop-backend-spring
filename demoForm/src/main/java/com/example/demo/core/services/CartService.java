package com.example.demo.core.services;

import com.example.demo.api.rest.CatalogController;
import com.example.demo.core.models.CartEntity;
import com.example.demo.core.models.CatalogEntity;
import com.example.demo.core.repos.CartRepository;
import com.example.demo.core.repos.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CatalogService catalogService;

    @Autowired
    public CartService(CartRepository cartRepository, CatalogService catalogService) {
        this.cartRepository = cartRepository;
        this.catalogService = catalogService;
    }


    public CartEntity createCart(CartEntity cartEntity, UUID productId) {
        var temp = catalogService.getById(productId);
        CartEntity newCartEntity = new CartEntity();
        newCartEntity.setProductId(temp.get().getId());
        newCartEntity.setUserId(UUID.randomUUID());
        newCartEntity.setCatalogProductName(temp.get().getProductName());
        newCartEntity.setCatalogProductPrice(temp.get().getProductPrice());
        newCartEntity.setProductCost(cartEntity.getProductCost());
        newCartEntity.setProductDiscount(cartEntity.getProductDiscount());
        newCartEntity.setProductFinalPrice(cartEntity.getProductFinalPrice());
        newCartEntity.setSelectedProductKol(cartEntity.getSelectedProductKol());
        newCartEntity.setCatalogProductPhoto(temp.get().getProductPhoto());
        return cartRepository.save(newCartEntity);
    }

    public Page<CartEntity> getAllCart(String filter, Pageable pageable) {
        if (filter != null && !filter.isBlank()) {
            var result = cartRepository.findByCatalogProductNameLike("%" + filter + "%", pageable);
            return result;
        }
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
