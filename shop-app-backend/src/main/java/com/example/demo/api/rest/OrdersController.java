package com.example.demo.api.rest;

import com.example.demo.core.models.OrdersEntity;
import com.example.demo.core.services.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<OrdersEntity>> getOrdersForUser(@PathVariable(name = "id")UUID id) {
        var result = ordersService.getAllByID(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<OrdersEntity> generateOrder() {
        OrdersEntity result = ordersService.generateOrder();
        return ResponseEntity.ok(result);
    }
}
