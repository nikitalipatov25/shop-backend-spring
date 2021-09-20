package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.NewCart;
import com.nikitalipatov.handmadeshop.core.models.NewOrder;
import com.nikitalipatov.handmadeshop.core.models.OrderStatus;
import com.nikitalipatov.handmadeshop.core.repositories.NewOrderRepository;
import com.nikitalipatov.handmadeshop.core.repositories.OrderStatusRepository;
import com.nikitalipatov.handmadeshop.dto.OrderDTO;
import com.nikitalipatov.handmadeshop.dto.OrderStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class NewOrderService {

    private final UserService userService;
    private final NewCartService newCartService;
    private final NewOrderRepository newOrderRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public NewOrderService(UserService userService, NewCartService newCartService, NewOrderRepository newOrderRepository, OrderStatusRepository orderStatusRepository) {
        this.userService = userService;
        this.newCartService = newCartService;
        this.newOrderRepository = newOrderRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    public List<OrderStatus> getOrderStatus() {
        return orderStatusRepository.findAll();
    }

    public NewOrder createOrder(OrderDTO orderDTO, HttpServletRequest request) {
        List<NewCart> userCart = newCartService.findUserCart(orderDTO.getProducts(), request);
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
        /*
        Не забыть очистить корзину после оформления заказа
        и не забыть изменить данные юзера, если отмечен соотв. чекбокс
         */
        if (orderDTO.isChangeData()) {
            String extraInformation = "Получатель: " + orderDTO.getFullName() + ". Номер телефона: " + orderDTO.getPhoneNumber();
            if (orderDTO.getOrderType().equals("Доставка")) {
                extraInformation = extraInformation + ". Адрес: " + orderDTO.getAddress();
            }
            newOrder.setExtraInformation(extraInformation);
        }
        return newOrderRepository.save(newOrder);
    }

    public Page<NewOrder> getOrders(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0,4, Sort.by("date")); // не забыть передавать номера страниц
        return newOrderRepository.findAllByUserId(userService.findUser(request).get().getId(), pageable);
    }

    public Optional<NewOrder> modifyOrderStatus(OrderStatusDTO orderStatusDTO) {
        Optional<NewOrder> result = newOrderRepository.findByOrderId(orderStatusDTO.getOrderId());
        return result
                .map(e -> {
                  e.setOrderStatus(orderStatusDTO.getOrderStatus());
                  return newOrderRepository.save(e);
                });
    }

    public Optional<NewOrder> getOrder(UUID id) {
        return newOrderRepository.findByOrderId(id);
    }
}
