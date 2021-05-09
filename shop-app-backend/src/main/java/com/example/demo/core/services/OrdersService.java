package com.example.demo.core.services;

import com.example.demo.core.models.OrdersEntity;
import com.example.demo.core.repos.OrdersRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CartService cartService;
    private final UserService userService;

    @Autowired
    public OrdersService (OrdersRepository ordersRepository, CartService cartService, UserService userService) {
        this.ordersRepository = ordersRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    public OrdersEntity generateOrder() {
        UUID singleUserId = UUID.fromString("cd668994-a73a-4da6-8f03-e7fe7034aa17"); // заменить, когда будет несколько пользователей
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderId(UUID.randomUUID());
        ordersEntity.setUserId(singleUserId);
        var cart = cartService.findCartByUserID(singleUserId);
        String productsInfo = "";
        int totalItems = 0;
        double totalCost = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            productsInfo = productsInfo + "Артикул: " + cart.get(i).getProductId() + " Кол-во: " + cart.get(i).getSelectedProductKol() + " ";
            totalItems = totalItems + cart.get(i).getSelectedProductKol();
            totalCost = totalCost + cart.get(i).getProductCost();
        }
        ordersEntity.setProductsInfo(productsInfo);
        if (totalItems >= 10) {
            double discount = totalCost * 0.05;
            ordersEntity.setFinalPrice(totalCost - discount);
        } else {
            ordersEntity.setFinalPrice(totalCost);
        }
        return ordersRepository.save(ordersEntity);
    }

    public List<OrdersEntity> getAllByID(UUID userId) {
        return ordersRepository.findByUserId(userId);
    }
}


