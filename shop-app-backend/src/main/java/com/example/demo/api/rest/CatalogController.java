package com.example.demo.api.rest;

import com.example.demo.core.models.CatalogEntity;
import com.example.demo.core.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Page<CatalogEntity>> getCatalog(@RequestParam(name = "search", required = false)String searching,
                                                          @RequestParam(name = "category", required = false)String category,
                                                          @RequestParam(name = "checkboxes", required = false)String[] checkboxes,
                                                          Pageable pageable) {
        Page<CatalogEntity> catalogEntityList = catalogService.listAll(searching, category, checkboxes, pageable);
        return ResponseEntity.ok(catalogEntityList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogEntity> getItem(@PathVariable(name = "id") UUID id) {
        Optional<CatalogEntity> result = catalogService.getById(id);
        return result
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CatalogEntity> addItem(@RequestBody CatalogEntity catalogEntity) {
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

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CatalogEntity> editItem(@PathVariable(name = "id")UUID id, @RequestBody CatalogEntity catalogEntity) {
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") UUID id) {
        Optional<Boolean> result = catalogService.deleteById(id);
        return result
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }




}
