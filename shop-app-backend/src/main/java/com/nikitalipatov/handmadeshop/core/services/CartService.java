package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.CatalogEntity;
import com.nikitalipatov.handmadeshop.supportingClasses.CartAnalyzer;
import com.nikitalipatov.handmadeshop.core.models.CartEntity;
import com.nikitalipatov.handmadeshop.core.repos.CartRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    public CartEntity addItem(UUID productId, String token) {
        Optional<CatalogEntity> product = catalogService.getById(productId);
        CartEntity newCartItem = new CartEntity();
        newCartItem.setProductId(product.get().getId());
        newCartItem.setUserName(getUsername(token));
        newCartItem.setCatalogProductName(product.get().getProductName());
        newCartItem.setCatalogProductPrice(product.get().getProductPrice());
        newCartItem.setSelectedProductKol(1);
        newCartItem.setProductCost(product.get().getProductPrice());
        newCartItem.setCatalogProductPhoto(product.get().getProductPhoto());
        return cartRepository.save(newCartItem);
    }

    public Optional<CartEntity> modifyItem(UUID id, CartEntity cartEntity) {
        Optional<CartEntity> result = cartRepository.findByProductId(id);
        Optional<CatalogEntity> check = catalogService.getById(id);
        return result
                .map(entity -> {
                    if (cartEntity.getSelectedProductKol() > check.get().getProductKol()) {
                        entity.setSelectedProductKol(check.get().getProductKol());
                    } else if (cartEntity.getSelectedProductKol() < 1) {
                        entity.setSelectedProductKol(1);
                    } else {
                        entity.setSelectedProductKol(cartEntity.getSelectedProductKol());
                    }
                    double a = entity.getCatalogProductPrice();
                    int b = entity.getSelectedProductKol();
                    double productCost = cartAnalyzer.calculateItemCost(a, b);
                    entity.setProductCost(productCost);
                    double prductDiscount;
                    if (b >= 10) {
                        prductDiscount = productCost * 0.05;
                    } else {
                        prductDiscount = 0.0;
                    }
                    double finalSummary = productCost - prductDiscount;
                    entity.setProductDiscount(prductDiscount);
                    entity.setFinalSummary(finalSummary);
                    return cartRepository.save(entity);
                });
    }

    public Page<CartEntity> getAllCart(String user, Pageable pageable) {
            return cartRepository.findAllByUserName(user, pageable);
        }

    public ArrayList<Object> getCartSummary(String user) {
        return cartAnalyzer.resultOfCalculation(cartRepository.findAllByUserName(user));
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

    public List<CartEntity> findCartByUserID(String username) {
        return cartRepository.findAllByUserName(username);
    }

    public void deleteAllUserCart(String username) {
        cartRepository.deleteAllByUserName(username);
    }

    public String getUsername(String token) {
        String secretKey = "nickLipa";
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }




}
