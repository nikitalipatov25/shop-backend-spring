package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.NewOrder;
import com.nikitalipatov.handmadeshop.core.models.OrderStatus;
import com.nikitalipatov.handmadeshop.core.repositories.OrderStatusRepository;
import com.nikitalipatov.handmadeshop.core.services.NewOrderService;
import com.nikitalipatov.handmadeshop.helpers.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/order")
public class NewOrderController {

    private final NewOrderService newOrderService;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public NewOrderController(NewOrderService newOrderService, OrderStatusRepository orderStatusRepository) {
        this.newOrderService = newOrderService;
        this.orderStatusRepository = orderStatusRepository;
    }

//    @GetMapping("/status")
//    public ResponseEntity<List<OrderStatus>> getOrderStatus() {
//        return ResponseEntity.ok(orderStatusRepository.findAll());
//    }

    @PostMapping("/add")
    public ResponseEntity<NewOrder> createOrder(@RequestBody OrderDTO orderDTO, HttpServletRequest request) {
        NewOrder result = newOrderService.createOrder(orderDTO, request);
        return ResponseEntity.ok(result);
    }

}
