package com.nikitalipatov.handmadeshop.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.core.services.ProductService;
import com.nikitalipatov.handmadeshop.dto.ModifyProductDTO;
import com.nikitalipatov.handmadeshop.dto.ProductFilterDTO;
import com.nikitalipatov.handmadeshop.dto.productOnSaleDTO;
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

    @GetMapping("/popular")
    public ResponseEntity<List<Product>> getPopularProducts() {
        List<Product> popularProducts = productService.getPopularProducts();
        return ResponseEntity.ok(popularProducts);
    }

    @GetMapping("/new")
    public ResponseEntity<List<Product>> getNewProducts() {
        List<Product> newProducts = productService.getNewProducts();
        return ResponseEntity.ok(newProducts);
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

    @PostMapping("/add")
    public ResponseEntity<Product> addItem(@RequestBody Product product) {
        Product resSave = productService.save(product);
        return ResponseEntity.ok(resSave);
    }

    @PutMapping(value = "/modify/{id}")
    public ResponseEntity<Product> editItem(@PathVariable(name = "id") UUID id, @RequestBody ModifyProductDTO modifyProductDTO) {
        Optional<Product> resEdit = productService.editCatalog(id, modifyProductDTO);
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

//    @GetMapping("/sale/{id}")
//    public ResponseEntity<Set<productOnSaleDTO>> getProductsOnSale(@PathVariable(name = "id")UUID id) {
//        return productService.getProductsOnSale(id);
//    }
}
