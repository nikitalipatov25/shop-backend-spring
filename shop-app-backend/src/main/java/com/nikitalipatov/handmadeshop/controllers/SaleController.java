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
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/get")
    public ResponseEntity<Page<Sale>> getSales() {
        Page<Sale> result = saleService.getSales();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteSale(@PathVariable(name = "id")UUID saleId) {
        Optional<Boolean> result = saleService.deleteSaleManually(saleId);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/modify")
    public ResponseEntity<Sale> modifySale(@RequestBody SaleDTO saleDTO) {
        Optional<Sale> result = saleService.modifySale(saleDTO);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
