package com.example.demo.core.services;

import com.example.demo.SearchParameterAnalyzer;
import com.example.demo.core.repos.CatalogRepository;
import com.example.demo.core.models.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;
    SearchParameterAnalyzer searchParameterAnalyzer = new SearchParameterAnalyzer();

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public Page<CatalogEntity> listAll(String filter, Pageable pageable) {
        if (filter != null && !filter.isBlank()) {
            searchParameterAnalyzer.analyzingSearchParameter(filter);
            String productName = searchParameterAnalyzer.getNameOfSearchedProduct();
            double priceTo = searchParameterAnalyzer.getTopLineOfPriceInSearchedProduct();
            double priceFrom = searchParameterAnalyzer.getBottomLineOfPriceInSearchedProduct();
            if (productName == null) {
                var result = catalogRepository.findByProductPriceBetween(priceFrom, priceTo, pageable);
                return result;
            } else {
                var result = catalogRepository.findByProductNameLikeAndProductPriceBetween("%" + productName + "%", priceFrom, priceTo, pageable);
                return result;
            }
        } else {
            return catalogRepository.findAll(pageable);
        }
    }

    public Optional<CatalogEntity> getById(UUID id) {
        return catalogRepository.findById(id);
    }

    public boolean validateEntity(CatalogEntity catalogEntity) {
        boolean result = true; // реализация метода
        return result;
    }

    public CatalogEntity save(CatalogEntity catalogEntity) {
        CatalogEntity newEntity = new CatalogEntity();
        newEntity.setId(UUID.randomUUID());
        newEntity.setProductDescription(catalogEntity.getProductDescription());
        newEntity.setProductKol(catalogEntity.getProductKol());
        newEntity.setProductName(catalogEntity.getProductName());
        newEntity.setProductPhoto(catalogEntity.getProductPhoto());
        newEntity.setProductPrice(catalogEntity.getProductPrice());
        return catalogRepository.save(newEntity);
    }

    public Optional<CatalogEntity> editCatalog(UUID uuid, CatalogEntity catalogEntity) {
        Optional<CatalogEntity> result = catalogRepository.findById(uuid);
        return result
                .map(entity -> {
                    entity.setProductDescription(catalogEntity.getProductDescription());
                    entity.setProductKol(catalogEntity.getProductKol());
                    entity.setProductPhoto(catalogEntity.getProductPhoto());
                    entity.setProductPrice(catalogEntity.getProductPrice());
                    entity.setProductName(catalogEntity.getProductName());
                    entity.setProductDescription(catalogEntity.getProductDescription());
                    return catalogRepository.save(entity);
                });

    }

    public Optional<Boolean> deleteById(UUID id) {
        Optional<CatalogEntity> result = catalogRepository.findById(id);
        return result
                .map(catalogEntity -> {
                    catalogRepository.deleteById(id);
                    return true;
                });
    }
}
