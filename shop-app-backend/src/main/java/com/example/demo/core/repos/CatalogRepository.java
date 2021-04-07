package com.example.demo.core.repos;

import com.example.demo.core.models.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {

    Page<CatalogEntity> findByProductNameLike(String productName, Pageable pageable);

    Page<CatalogEntity> findByProductPriceBetween(double priceFrom, double priceTo, Pageable pageable);

    Page<CatalogEntity> findByCategoryType(String categoryType, Pageable pageable);
}
