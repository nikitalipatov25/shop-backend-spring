package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.NewCart;
import com.nikitalipatov.handmadeshop.core.models.NewOrder;
import com.nikitalipatov.handmadeshop.core.repositories.NewOrderRepository;
import com.nikitalipatov.handmadeshop.helpers.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NewOrderService {

    private final UserService userService;
    private final NewCartService newCartService;
    private final NewOrderRepository newOrderRepository;

    @Autowired
    public NewOrderService(UserService userService, NewCartService newCartService, NewOrderRepository newOrderRepository) {
        this.userService = userService;
        this.newCartService = newCartService;
        this.newOrderRepository = newOrderRepository;
    }

    public NewOrder createOrder(OrderDTO orderDTO, HttpServletRequest request) {
        var userCart = newCartService.findUserCart(orderDTO.getProducts(), request);
        NewOrder newOrder = new NewOrder();
        newOrder.setOrderId(UUID.randomUUID());
        newOrder.setUserId(userService.findUser(request).get().getId());
        List<String> productsInfo = new ArrayList<>();
        for (NewCart newCart : userCart) {
            String infoAboutProduct = "Артикул: " + newCart.getProductId() +
                    ", название: " + newCart.getProduct().getName() +
                    ", количество: " + newCart.getAmount();
            productsInfo.add(infoAboutProduct);
        }
        newOrder.setProductsInfo(productsInfo);
        newOrder.setSummary((Double) newCartService.cartSummaryForOrders(orderDTO.getProducts(), request).get(3)); // 3 - price with discount
        newOrder.setDate(new Date());
        newOrder.setOrderStatus("Оформлен");
        newOrder.setOrderType(orderDTO.getOrderType());
        return newOrderRepository.save(newOrder);
    }
}
