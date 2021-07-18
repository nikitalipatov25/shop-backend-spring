package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Catalog;
import com.nikitalipatov.handmadeshop.core.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(allowedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Headers", "Access-Control-Allow-Methods" })
@RestController
@RequestMapping(value = "/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping()
    public ResponseEntity<Page<Catalog>> getCatalog(Pageable pageable) {
        Page<Catalog> catalogEntityList = catalogService.listAll(pageable);
        return ResponseEntity.ok(catalogEntityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getItem(@PathVariable(name = "id") UUID id) {
        Optional<Catalog> result = catalogService.getById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Catalog> addItem(@RequestBody Catalog catalog) {
       // проверить валидность catalogEntity
        boolean result = catalogService.validateEntity(catalog);
        // если фальш выдаем ошибку bedRequest
        // если правда - вызываем метод save
        if (result) {
           Catalog resSave = catalogService.save(catalog);
           return ResponseEntity.ok(resSave);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/modify/{id}")
    public ResponseEntity<Catalog> editItem(@PathVariable(name = "id")UUID id, @RequestBody Catalog catalog) {
        boolean result = catalogService.validateEntity(catalog);
        if (result) {
            Optional<Catalog> resEdit = catalogService.editCatalog(id, catalog);
            return resEdit
                    .map(entity -> ResponseEntity.ok(entity))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") UUID id) {
        Optional<Boolean> result = catalogService.deleteById(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }




}
