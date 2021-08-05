package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Animal;
import com.nikitalipatov.handmadeshop.core.models.Category;
import com.nikitalipatov.handmadeshop.core.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAnimalsList() {
        List<Animal> result = adminService.getAnimalList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/animal/{name}")
    public ResponseEntity<List<Animal>> getAnimalsListByName(@PathVariable(name = "name") String name) {
        List<Animal> result = adminService.getAnimalsListByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/add/{animal}/{categoryArray}")
    public ResponseEntity<Animal> addAnimal(@PathVariable(name = "animal") String animal, @PathVariable(name = "categoryArray") String[] categoryArray) {
        Animal result = adminService.addAnimal(animal, categoryArray);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategoriesList() {
        List<Category> result = adminService.getCategoriesList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<Category>> getCategoriesListByName(@PathVariable(name = "name") String name) {
        List<Category> result = adminService.getCategoriesListByName(name);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/animals/{name}/category/add")
    public ResponseEntity<Category> addCategory(@PathVariable(name = "name") String[] animalArray, @RequestBody Category category) {
        Category result = adminService.addCategory(animalArray, category);
        return ResponseEntity.ok(result);
    }
}
