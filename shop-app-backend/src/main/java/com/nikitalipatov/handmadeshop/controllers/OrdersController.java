package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Orders;
import com.nikitalipatov.handmadeshop.core.services.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping()
    public ResponseEntity<Page<Orders>> getUserOrders(HttpServletRequest request, Pageable pageable) {
        var result = ordersService.getUserOrders(request, pageable);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<Orders> generateOrder(@RequestBody Orders orders, HttpServletRequest request) {
        Orders result = ordersService.generateOrder(orders, request);
        return ResponseEntity.ok(result);
    }

}
