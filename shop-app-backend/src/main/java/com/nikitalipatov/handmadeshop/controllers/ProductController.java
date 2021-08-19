package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.services.ProductService;
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
@RequestMapping(value = "/catalog")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getCatalog(Pageable pageable) {
        Page<Product> catalogEntityList = productService.listAll(pageable);
        return ResponseEntity.ok(catalogEntityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getItem(@PathVariable(name = "id") UUID id) {
        Optional<Product> result = productService.getById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addItem(@RequestBody Product product) {
           Product resSave = productService.save(product);
           return ResponseEntity.ok(resSave);
    }

    @PutMapping(value = "/modify/{id}")
    public ResponseEntity<Product> editItem(@PathVariable(name = "id")UUID id, @RequestBody Product product) {
            Optional<Product> resEdit = productService.editCatalog(id, product);
            return resEdit
                    .map(entity -> ResponseEntity.ok(entity))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") UUID id) {
        Optional<Boolean> result = productService.deleteById(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }




}
