package com.example.demo.core.services;

import com.example.demo.core.models.CartEntity;
import com.example.demo.core.models.OrdersEntity;
import com.example.demo.core.models.UserEntity;
import com.example.demo.core.repos.OrdersRepository;
import com.example.demo.core.supportingClasses.CartInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CartService cartService;
    private final UserService userService;
    private final CatalogService catalogService;

    @Autowired
    public OrdersService (OrdersRepository ordersRepository, CartService cartService, UserService userService, CatalogService catalogService) {
        this.ordersRepository = ordersRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.catalogService = catalogService;
    }

    public OrdersEntity generateOrder(String orderType) {
        var user = userService.getUserById(UUID.fromString("cd668994-a73a-4da6-8f03-e7fe7034aa17"));
        var cart = cartService.findCartByUserID("a@mail.ru");
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderId(UUID.randomUUID());
        ordersEntity.setUserId(user.get().getId());
        ordersEntity.setUserFIO(user.get().getFullName());
        ordersEntity.setUserPhoneNumber(user.get().getPhoneNumber());
        ordersEntity.setUserAddress(user.get().getAddress());
        ordersEntity.setOrderType(orderType);
        ordersEntity.setOrderStatus("Принят в магазине");
        ordersEntity.setOrderDate(calculateOrderDate());
        CartInfo cartInfo = calculateCart(cart);
        ordersEntity.setProductsInfo(cartInfo.getProductsInfo());
        ordersEntity.setFinalPrice(cartInfo.getTotalCost());
        /*
        ВРЕМЕННЫЕ КАСТЫЛИ
        */
        reorganizeCatalog(cart);
        cartService.deleteAllUserCart("a@mail.ru");
        return ordersRepository.save(ordersEntity);

    }

    public List<OrdersEntity> getAllByID(UUID userId) {
        return ordersRepository.findByUserId(userId);
    }

    public String calculateOrderDate() {
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(currentDate);
    }

    public CartInfo calculateCart(List<CartEntity> cart) {
        CartInfo cartInfo = new CartInfo();
        String productsInfo = "";
        int totalItems = 0;
        double totalCost = 0.0;
        double discount = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            productsInfo = productsInfo + "Товар: " + cart.get(i).getCatalogProductName() + " Кол-во: " + cart.get(i).getSelectedProductKol() + " ";
            totalItems = totalItems + cart.get(i).getSelectedProductKol();
            totalCost = totalCost + cart.get(i).getProductCost();
        }
        cartInfo.setProductsInfo(productsInfo);
        cartInfo.setTotalItems(totalItems);
        if (totalItems >= 10) {
            discount = totalCost * 0.05;
            totalCost = totalCost - discount;
        }
        cartInfo.setTotalCost(totalCost);
        return cartInfo;
    }

    public void reorganizeCatalog(List<CartEntity> cart) {
        for (int i = 0; i < cart.size(); i++) {
            catalogService.modifyKolInCatalog(cart.get(i).getProductId(), cart.get(i).getSelectedProductKol());
        }

    }

}


