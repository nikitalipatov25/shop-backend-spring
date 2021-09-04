package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.services.AdminService;
import com.nikitalipatov.handmadeshop.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

    @GetMapping("/get/animals")
    public ResponseEntity<Set<Animal>> getAnimalsSet() {
        Set<Animal> result = adminService.getAnimalsSet();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/animal/{name}")
    public ResponseEntity<Optional<Animal>> getAnimal(@PathVariable(name = "name") String name) {
        Optional<Animal> result = adminService.getAnimal(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/categories")
    public ResponseEntity<List<Category>> getCategoriesList() {
        List<Category> result = adminService.getCategoriesList();
        return ResponseEntity.ok(result);
    }
}
