package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Promotion;
import com.nikitalipatov.handmadeshop.core.services.PromotionService;
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
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping()
    public ResponseEntity<?> createPromotion ( @RequestBody Promotion promotion, @RequestParam(name = "products", required = false)String[] products) {
        var result = promotionService.create(promotion, products);
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<Page<Promotion>> getPromotions(Pageable pageable) {
        var result = promotionService.getPromotions(pageable);
        return ResponseEntity.ok(result);
    }
}
