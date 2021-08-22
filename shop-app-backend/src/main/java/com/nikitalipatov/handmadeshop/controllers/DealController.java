package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Deal;
import com.nikitalipatov.handmadeshop.core.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/promotions")
public class DealController {

    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping()
    public ResponseEntity<?> createPromotion (@RequestBody Deal deal, @RequestParam(name = "products", required = false)String[] products) {
        var result = dealService.create(deal, products);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Page<Deal>> getPromotions(Pageable pageable) {
        var result = dealService.getPromotions(pageable);
        return ResponseEntity.ok(result);
    }
}
