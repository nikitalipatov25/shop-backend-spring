package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Sale;
import com.nikitalipatov.handmadeshop.core.services.SaleService;
import com.nikitalipatov.handmadeshop.dto.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Sale> createSale(@RequestBody SaleDTO saleDTO) {
        Sale newSale = saleService.createSale(saleDTO);
        saleService.addSaleToProduct(newSale, saleDTO);
        return ResponseEntity.ok(newSale);
    }

    @GetMapping()
    public ResponseEntity<Page<Sale>> getPromotions(Pageable pageable) {
        var result = saleService.getPromotions(pageable);
        return ResponseEntity.ok(result);
    }
}
