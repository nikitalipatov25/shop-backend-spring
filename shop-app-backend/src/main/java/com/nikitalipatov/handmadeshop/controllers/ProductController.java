package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.ProductRepository;
import com.nikitalipatov.handmadeshop.core.services.ProductService;
import com.nikitalipatov.handmadeshop.helpers.FilterDTO;
import com.nikitalipatov.handmadeshop.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @GetMapping("/{animal}/{category}")
    public List<Product> filter(@PathVariable(name = "animal")String animal, @PathVariable(name = "category")String[] category) {
        Specification<Product> specification = Specification.where(ProductSpecifications.equalsAnimal(animal).and(ProductSpecifications.inCategories(category)));
        return productRepository.findAll(specification);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getItem(@PathVariable(name = "id") UUID id) {
        Optional<Product> result = productService.getById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
