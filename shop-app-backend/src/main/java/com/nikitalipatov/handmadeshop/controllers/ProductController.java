package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.core.services.ProductService;
import com.nikitalipatov.handmadeshop.dto.ProductFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getCatalog(Pageable pageable) {
        Page<Product> catalogEntityList = productService.listAll(pageable);
        return ResponseEntity.ok(catalogEntityList);
    }

    @PostMapping("/filter")
    public ResponseEntity<Page<Product>> filterProducts(@RequestBody ProductFilterDTO productFilterDTO) {
        return ResponseEntity.ok(productService.findAllWithFilters(productFilterDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getItem(@PathVariable(name = "id") UUID id) {
        Optional<Product> result = productService.getById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
