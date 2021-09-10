package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.services.CategoryService;
import com.nikitalipatov.handmadeshop.dto.AnimalDTO;
import com.nikitalipatov.handmadeshop.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity<Set<Category>> getCategoriesSet() {
        Set<Category> result = categoryService.getCategoriesSet();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable(name = "id")UUID id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryDTO categoryDTO) {
        Category result = categoryService.addNewCategory(categoryDTO);
        categoryService.addNewCategoryToAnimals(categoryDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") UUID id) {
        Optional<Boolean> result = categoryService.deleteCategory(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Category> editCategory(@PathVariable(name = "id")UUID id, @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> result = categoryService.editCategory(id, categoryDTO);
        return result
                .map(e -> ResponseEntity.ok(e))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
