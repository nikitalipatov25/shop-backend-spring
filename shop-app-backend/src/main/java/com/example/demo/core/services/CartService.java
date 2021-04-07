package com.example.demo.core.services;

import com.example.demo.core.supportingClasses.CartAnalyzer;
import com.example.demo.core.models.CartEntity;
import com.example.demo.core.repos.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CatalogService catalogService;
    CartAnalyzer cartAnalyzer = new CartAnalyzer();

    @Autowired
    public CartService(CartRepository cartRepository, CatalogService catalogService) {
        this.cartRepository = cartRepository;
        this.catalogService = catalogService;
    }

    public CartEntity createCart(CartEntity cartEntity, UUID productId) {
        var temp = catalogService.getById(productId);
        CartEntity newCartEntity = new CartEntity();
        newCartEntity.setProductId(temp.get().getId());
        UUID singleUserId = UUID.fromString("cd668994-a73a-4da6-8f03-e7fe7034aa17");
        newCartEntity.setUserId(singleUserId);
        newCartEntity.setCatalogProductName(temp.get().getProductName());
        newCartEntity.setCatalogProductPrice(temp.get().getProductPrice());
        newCartEntity.setSelectedProductKol(cartEntity.getSelectedProductKol());
        double productCost = cartAnalyzer.calculateItemCost(temp.get().getProductPrice(), cartEntity.getSelectedProductKol());
        newCartEntity.setProductCost(productCost); // здесь установка стоимости товара при добавлении
        newCartEntity.setCatalogProductPhoto(temp.get().getProductPhoto());
        return cartRepository.save(newCartEntity);
    }

    public Optional<CartEntity> modifyItem(UUID id, CartEntity cartEntity) {
        Optional<CartEntity> result = cartRepository.findByProductId(id);
        return result
                .map(entity -> {
                    entity.setSelectedProductKol(cartEntity.getSelectedProductKol());
                    double productCost = cartAnalyzer.calculateItemCost(cartEntity.getCatalogProductPrice(), cartEntity.getSelectedProductKol());
                    entity.setProductCost(productCost); // здесь установка стоимости товара при добавлении
                    return cartRepository.save(entity);
                });
    }

    public Page<CartEntity> getAllCart(String filter, Pageable pageable) {
        if (filter != null && !filter.isBlank()) {
            var result = cartRepository.findByCatalogProductNameLike("%" + filter + "%", pageable);
            return result;
        } else {
            var result = cartRepository.findAll(pageable);
            return result;
        }
    }

    public ArrayList<Object> getCartSummary() {
        var result = cartRepository.findAll();
        return cartAnalyzer.resultOfCalculation(result);
    }

    public Optional<CartEntity> getById(UUID id) {
        return cartRepository.findByProductId(id);
    }


    public Optional<Boolean> deleteCartItem(UUID id) {
        Optional<CartEntity> deletedItem = cartRepository.findByProductId(id);
        return deletedItem
                .map(cartEntity -> {
                    cartRepository.deleteByProductId(id);
                    return true;
                });
    }


}
