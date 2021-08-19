package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.helpers.CartAnalyzer;
import com.nikitalipatov.handmadeshop.core.models.Cart;
import com.nikitalipatov.handmadeshop.core.repositories.CartRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    CartAnalyzer cartAnalyzer = new CartAnalyzer();



    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart addItem(UUID productUUID, HttpServletRequest request) {
        Optional<Product> product = productService.getById(productUUID);
        Cart newCartItem = new Cart();
        String header = request.getHeader("Authorization");
        String token = header.substring(7, header.length());
        String username = Jwts.parser().setSigningKey("bezKoderSecretKey").parseClaimsJws(token).getBody().getSubject();
        newCartItem.setUserName(username);
        newCartItem.setProductId(product.get().getId());
        newCartItem.setCatalogProductName(product.get().getName());
        newCartItem.setCatalogProductPrice(product.get().getPrice());
        newCartItem.setSelectedProductKol(1);
        newCartItem.setProductCost(product.get().getPrice());
        newCartItem.setCatalogProductPhoto(product.get().getImage());
        return cartRepository.save(newCartItem);
    }

    public Optional<Cart> modifyItem(UUID id, Cart cart) {
        Optional<Cart> result = cartRepository.findByProductId(id);
        Optional<Product> check = productService.getById(id);
        return result
                .map(entity -> {
                    if (cart.getSelectedProductKol() > check.get().getAmount()) {
                        entity.setSelectedProductKol(check.get().getAmount());
                    } else if (cart.getSelectedProductKol() < 1) {
                        entity.setSelectedProductKol(1);
                    } else {
                        entity.setSelectedProductKol(cart.getSelectedProductKol());
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

    public Page<Cart> getAllCart(String user, Pageable pageable) {
            return cartRepository.findAllByUserName(user, pageable);
        }

    public ArrayList<Object> getCartSummary(String user) {
        return cartAnalyzer.resultOfCalculation(cartRepository.findAllByUserName(user));
    }

    public Optional<Cart> getById(UUID id) {
        return cartRepository.findByProductId(id);
    }


    public Optional<Boolean> deleteCartItem(UUID id) {
        Optional<Cart> deletedItem = cartRepository.findByProductId(id);
        return deletedItem
                .map(cartEntity -> {
                    cartRepository.deleteByProductId(id);
                    return true;
                });
    }

    public List<Cart> findCartByUserID(String username) {
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
