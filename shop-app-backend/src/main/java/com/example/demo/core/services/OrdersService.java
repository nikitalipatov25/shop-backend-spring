package com.example.demo.core.services;

import com.example.demo.core.models.OrdersEntity;
import com.example.demo.core.repos.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    public OrdersEntity generateOrder(String orderType) {
        var user = userService.getUserById(UUID.fromString("cd668994-a73a-4da6-8f03-e7fe7034aa17"));
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderId(UUID.randomUUID());
        ordersEntity.setUserId(user.get().getId());
        ordersEntity.setUserFIO(user.get().getFullName());
        ordersEntity.setUserPhoneNumber(user.get().getPhoneNumber());
        ordersEntity.setUserAddress(user.get().getAddress());
        ordersEntity.setOrderType(orderType);
        ordersEntity.setOrderStatus("Принят в магазине");
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String orderDate = simpleDateFormat.format(currentDate);
        ordersEntity.setOrderDate(orderDate);
        var cart = cartService.findCartByUserID(user.get().getId());
        String productsInfo = "";
        int totalItems = 0;
        double totalCost = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            productsInfo = productsInfo + "Товар: " + cart.get(i).getCatalogProductName() + " Кол-во: " + cart.get(i).getSelectedProductKol() + " ";
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


