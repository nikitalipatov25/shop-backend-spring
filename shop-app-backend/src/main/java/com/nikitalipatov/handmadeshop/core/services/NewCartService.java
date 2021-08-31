package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.NewCart;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.NewCartRepository;
import com.nikitalipatov.handmadeshop.helpers.CartSummary;
import com.nikitalipatov.handmadeshop.helpers.NewCartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class NewCartService {

    private final UserService userService;
    private final ProductService productService;
    private final NewCartRepository newCartRepository;



    @Autowired
    public NewCartService(UserService userService, ProductService productService, NewCartRepository newCartRepository) {
        this.userService = userService;
        this.productService = productService;
        this.newCartRepository = newCartRepository;
    }

    public NewCart addProductToCart(NewCartDTO newCartDTO, HttpServletRequest request) {
        NewCart newCart = new NewCart();
        newCart.setProductId(newCartDTO.getProductId());
        newCart.setUserId(userService.findUser(request).get().getId());
        newCart.setProduct(productService.getById(newCartDTO.getProductId()).get());
        newCart.setAmount(newCartDTO.getAmount());
        return newCartRepository.save(newCart);
    }

    public Page<NewCart> getUserCart(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0,4); // page size and number. need change when pagination on client
        return newCartRepository.findAllByUserId(userService.findUser(request).get().getId(), pageable);
    }

    public Optional<Boolean> deleteProductFromCart(UUID productId, HttpServletRequest request) {
        Optional<NewCart> result = newCartRepository.findByProductIdAndUserId(productId,
                userService.findUser(request).get().getId());
        return result
                .map(e -> {
                    newCartRepository.deleteByProductIdAndUserId(productId,
                            userService.findUser(request).get().getId());
                    return true;
                });
    }

    public Optional<NewCart> modifyProductAmountInCart(NewCartDTO newCartDTO, HttpServletRequest request) {
        Optional<NewCart> result = newCartRepository.findByProductIdAndUserId(newCartDTO.getProductId(),
                userService.findUser(request).get().getId());
        return result
                .map(e -> {
                    if (result.get().getProduct().getAmount() < newCartDTO.getAmount()) {
                        e.setAmount(result.get().getProduct().getAmount());
                    } else if (newCartDTO.getAmount() < 1) {
                        e.setAmount(1);
                    } else {
                        e.setAmount(newCartDTO.getAmount());
                    }
                    return newCartRepository.save(e);
                });
    }

    public List<Object> cartSummary(HttpServletRequest request) {
        List<NewCart> result= newCartRepository.findAllByUserId(userService.findUser(request).get().getId());
        List<Product> products = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        for (NewCart newCart : result) {
            products.add(newCart.getProduct());
            amounts.add(newCart.getAmount());
        }
        CartSummary cartSummary = new CartSummary();
        return cartSummary.cartSummary(products, amounts);
    }
}

