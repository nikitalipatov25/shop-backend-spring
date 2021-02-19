package com.example.demo.api.rest;

import com.example.demo.core.models.CatalogEntity;
import com.example.demo.core.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping()
    public ResponseEntity<Page<CatalogEntity>> getFullCatalog(@RequestParam(name = "search", required = false)String search, Pageable pageable) {
        Page<CatalogEntity> catalogEntityList = catalogService.listAll(search, pageable);
        return ResponseEntity.ok(catalogEntityList);
    }

    @GetMapping("/{id}/")
    public ResponseEntity<CatalogEntity> getCatalogItem(@PathVariable(name = "id") UUID id) {
        Optional<CatalogEntity> result = catalogService.getById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<CatalogEntity> createCatalog(@RequestBody CatalogEntity catalogEntity) {
       // проверить валидность catalogEntity
        boolean result = catalogService.validateEntity(catalogEntity);
        // если фальш выдаем ошибку bedRequest
        // если правда - вызываем метод save
        if (result) {
           CatalogEntity resSave = catalogService.save(catalogEntity);
           return ResponseEntity.ok(resSave);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}/")
    public ResponseEntity<CatalogEntity> editCatalogItem(@PathVariable(name = "id")UUID id, @RequestBody CatalogEntity catalogEntity) {
        boolean result = catalogService.validateEntity(catalogEntity);
        if (result) {
            Optional<CatalogEntity> resEdit = catalogService.editCatalog(id, catalogEntity);
            return resEdit
                    .map(entity -> ResponseEntity.ok(entity))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<?> deleteCatalogItem(@PathVariable(name = "id") UUID id) {
        Optional<Boolean> result = catalogService.deleteById(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }




}
