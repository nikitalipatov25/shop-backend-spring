package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Orders;
import com.nikitalipatov.handmadeshop.core.services.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(allowedHeaders = {
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Methods",
        "Content-Type"
},
        methods = { RequestMethod.POST,RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<Orders>> getUserOrders(@PathVariable(name = "id")UUID id) {
        var result = ordersService.getAllByID(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Orders> generateOrder(@RequestParam(name = "orderType")String orderType) {
        Orders result = ordersService.generateOrder(orderType);
        return ResponseEntity.ok(result);
    }

}
