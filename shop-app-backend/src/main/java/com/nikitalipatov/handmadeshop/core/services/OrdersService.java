package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Cart;
import com.nikitalipatov.handmadeshop.core.models.Orders;
import com.nikitalipatov.handmadeshop.core.repos.OrdersRepository;
import com.nikitalipatov.handmadeshop.supportingClasses.CartInfo;
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
    private final CatalogService catalogService;

    @Autowired
    public OrdersService (OrdersRepository ordersRepository, CartService cartService, CatalogService catalogService) {
        this.ordersRepository = ordersRepository;
        this.cartService = cartService;
        this.catalogService = catalogService;
    }

    public Orders generateOrder(String orderType) {
        var cart = cartService.findCartByUserID("a@mail.ru");
        Orders orders = new Orders();
        orders.setOrderId(UUID.randomUUID());
        orders.setOrderType(orderType);
        orders.setOrderStatus("Принят в магазине");
        orders.setOrderDate(calculateOrderDate());
        CartInfo cartInfo = calculateCart(cart);
        orders.setProductsInfo(cartInfo.getProductsInfo());
        orders.setFinalPrice(cartInfo.getTotalCost());
        /*
        ВРЕМЕННЫЕ КАСТЫЛИ
        */
        reorganizeCatalog(cart);
        cartService.deleteAllUserCart("a@mail.ru");
        return ordersRepository.save(orders);

    }

    public List<Orders> getAllByID(UUID userId) {
        return ordersRepository.findByUserId(userId);
    }

    public String calculateOrderDate() {
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(currentDate);
    }

    public CartInfo calculateCart(List<Cart> cart) {
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

    public void reorganizeCatalog(List<Cart> cart) {
        for (int i = 0; i < cart.size(); i++) {
            catalogService.modifyKolInCatalog(cart.get(i).getProductId(), cart.get(i).getSelectedProductKol());
        }

    }

}


