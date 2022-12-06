package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.NewCart;
import com.nikitalipatov.handmadeshop.core.models.NewOrder;
import com.nikitalipatov.handmadeshop.core.models.OrderStatus;
import com.nikitalipatov.handmadeshop.core.repositories.NewOrderRepository;
import com.nikitalipatov.handmadeshop.core.repositories.OrderStatusRepository;
import com.nikitalipatov.handmadeshop.dto.OrderDTO;
import com.nikitalipatov.handmadeshop.dto.OrderStatusDTO;
import com.nikitalipatov.handmadeshop.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class NewOrderService {

    private final UserService userService;
    private final NewCartService newCartService;
    private final NewOrderRepository newOrderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductService productService;

    @Autowired
    public NewOrderService(UserService userService, NewCartService newCartService, NewOrderRepository newOrderRepository, OrderStatusRepository orderStatusRepository, ProductService productService) {
        this.userService = userService;
        this.newCartService = newCartService;
        this.newOrderRepository = newOrderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.productService = productService;
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
            String infoAboutProduct =newCart.getProduct().getName() +
                    ", количество: " + newCart.getAmount();
            productsInfo.add(infoAboutProduct);
        }
        newOrder.setProductsInfo(productsInfo);
        newOrder.setSummary((Double) newCartService.cartSummaryForOrders(orderDTO.getProducts(), request).get(3)); // 3 - price with discount
        newOrder.setDate(LocalDateTime.now());
        newOrder.setOrderStatus("В обработке");
        if (orderDTO.getOrderType().equals("Доставка")) {
            newOrder.setOrderType(orderDTO.getOrderType() + " " + orderDTO.getAddress());
        } else {
            newOrder.setOrderType(orderDTO.getOrderType());
        }
        productService.modifyProductAmount(userCart);
        for (int i = 0; i < orderDTO.getProducts().size(); i++) {
            newCartService.deleteProductFromCart(orderDTO.getProducts().get(i), request);
        }
        return newOrderRepository.save(newOrder);
    }



    public List<NewOrder> getOrders(HttpServletRequest request) {
        return newOrderRepository.findAllByUserId(userService.findUser(request).get().getId());
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

    public Optional<Boolean> cancelOrder(UUID id, HttpServletRequest request) {
        Optional<NewOrder> result = newOrderRepository.findByOrderId(id);
        List<String> productsInfo = result.get().getProductsInfo();
        for (int i = 0; i < productsInfo.size(); i++) {
            int amount = Integer.parseInt(productsInfo.get(i).replaceAll("\\D+",""));
            String[] arr = productsInfo.get(i).split(",");
            String product = arr[0];
            productService.addAmountToProduct(product, amount);
        }
        return result.map(canceledOrder -> {
            newOrderRepository.deleteByOrderIdAndUserId(id,
                    userService.findUser(request).get().getId());
            return true;
        });
    }

    public Optional<NewOrder> confirmReceipt(UUID id) {
        Optional<NewOrder> result = newOrderRepository.findByOrderId(id);
        return result.map(entity ->{
            entity.setOrderStatus("Завершен");
            return newOrderRepository.save(entity);
        });
    }
}
