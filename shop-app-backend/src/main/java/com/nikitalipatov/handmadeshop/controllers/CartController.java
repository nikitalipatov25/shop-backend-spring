package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.supportingClasses.CartDTO;
import com.nikitalipatov.handmadeshop.core.models.Cart;
import com.nikitalipatov.handmadeshop.core.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(
        value = "/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/add")
    public ResponseEntity<Cart> addItem(@RequestParam(name = "productUUID")UUID productId, HttpServletRequest request) {
        Cart createdCart = cartService.addItem(productId, request);
        return ResponseEntity.ok(createdCart);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cart> modifyItem(@PathVariable(name = "id")UUID id, @RequestBody Cart cart) {
        Optional<Cart> result = cartService.modifyItem(id, cart);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCart(@RequestParam(name = "user")String user, Pageable pageable) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCatalogPage(cartService.getAllCart(user, pageable));
        cartDTO.setCartSummary(cartService.getCartSummary(user));
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getItem(@PathVariable(name = "id") UUID id) {
        Optional<Cart> result = cartService.getById(id);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id")UUID id) {
        Optional<Boolean> deletedItem = cartService.deleteCartItem(id);
        return deletedItem
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
