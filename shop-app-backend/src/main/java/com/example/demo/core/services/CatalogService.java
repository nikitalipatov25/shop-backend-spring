package com.example.demo.core.services;

import com.example.demo.core.supportingClasses.SearchParameterAnalyzer;
import com.example.demo.core.repos.CatalogRepository;
import com.example.demo.core.models.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogService {

    SearchParameterAnalyzer searchParameterAnalyzer = new SearchParameterAnalyzer();

    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public Page<CatalogEntity> listAll(String search, String category, String[] checkboxes, Pageable pageable) {
        Page<CatalogEntity> result = catalogRepository.findAll(pageable);
        searchParameterAnalyzer.analyzingSearchParameter(search);
        String productName = searchParameterAnalyzer.getProductName();
        double startPrice = searchParameterAnalyzer.getStartPrice();
        double endPrice = searchParameterAnalyzer.getEndPrice();
        try {
            if (category.equals("empty") == false) {
                if (productName != null && checkboxes.length != 0) {
                    result = catalogRepository.findByProductNameLikeAndProductPriceBetweenAndCategoryTypeAndCategoryNameIn
                            ("%" + productName + "%", startPrice, endPrice, category, checkboxes, pageable);
                } else if (productName != null && checkboxes.length == 0) {
                    result = catalogRepository.findByProductNameLikeAndProductPriceBetweenAndCategoryType
                            ("%" + productName + "%", startPrice, endPrice, category, pageable);
                } else if (productName == null && checkboxes.length != 0) {
                    result = catalogRepository.findByProductPriceBetweenAndCategoryTypeAndCategoryNameIn
                            (startPrice, endPrice, category, checkboxes, pageable);
                } else {
                    result = catalogRepository.findByProductPriceBetweenAndCategoryType
                            (startPrice, endPrice, category, pageable);
                }
            }
            if (category.equals("empty") == true) {
                if (productName != null && checkboxes.length != 0) {
                    result = catalogRepository.findByProductNameLikeAndProductPriceBetweenAndCategoryNameIn
                            ("%" + productName + "%", startPrice, endPrice, checkboxes, pageable);
                } else if (productName != null && checkboxes.length == 0) {
                    result = catalogRepository.findByProductNameLikeAndProductPriceBetween
                            ("%" + productName + "%", startPrice, endPrice, pageable);
                } else if (productName == null && checkboxes.length != 0) {
                    result = catalogRepository.findByProductPriceBetweenAndAndCategoryNameIn
                            (startPrice, endPrice, checkboxes, pageable);
                } else {
                    result = catalogRepository.findByProductPriceBetween
                            (startPrice, endPrice, pageable);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
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
