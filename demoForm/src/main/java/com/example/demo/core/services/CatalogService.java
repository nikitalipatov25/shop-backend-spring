package com.example.demo.core.services;

import com.example.demo.core.repos.CatalogRepository;
import com.example.demo.core.models.CatalogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.*;


@Service
public class CatalogService {


    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public Page<CatalogEntity> listAll(String search, Pageable pageable) {
        if (search != null && !search.isBlank()) {
            String resultOfSearch[] = searchAnalysis(search);
            String productName = resultOfSearch[0];
            double priceFrom = Double.parseDouble(resultOfSearch[1]);
            double priceTo = Double.parseDouble(resultOfSearch[2]);
            if (productName == null) {
                var result = catalogRepository.findByProductPriceBetween(priceFrom, priceTo, pageable);
                return result;
            } else {
                var result = catalogRepository.findByProductNameLikeAndProductPriceBetween("%" +productName + "%", priceFrom, priceTo, pageable);
                return result;
            }
        } else
            return catalogRepository.findAll(pageable);
        }


    public String[] searchAnalysis(String search) {
        String productName;
        String priceFrom;
        String priceTo;
        Pattern pattern = Pattern.compile("([а-яА-Я]+)?(\\s+)?(\\d+)?(\\s+)?-?(\\s+)?(\\d+)?(\\s+)?([а-яА-Я]+)?");
        Matcher matcher = pattern.matcher(search);
        matcher.find();
        productName = matcher.group(1);
        if (productName == null) {
            productName = matcher.group(8);
        }
        priceFrom = matcher.group(3);
        if (priceFrom == null) {
            priceFrom = "0";
        }
        priceTo = matcher.group(6);
        if (priceTo == null) {
            priceTo = "999999";
        }
        String[] result = new String[3];
        result[0] = productName;
        result[1] = priceFrom;
        result[2] = priceTo;
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
