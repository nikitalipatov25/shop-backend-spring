package com.example.demo.core.repos;

import com.example.demo.core.models.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {

    Page<CatalogEntity> findByProductNameLikeAndProductPriceBetweenAndCategoryTypeAndCategoryNameIn(String productName, double startPrice, double endPrice, String categoryType, String[] paramArray, Pageable pageable);

    Page<CatalogEntity> findByProductNameLikeAndProductPriceBetweenAndCategoryType(String productName, double startPrice, double endPrice, String categoryType, Pageable pageable);

    Page<CatalogEntity> findByProductPriceBetweenAndCategoryTypeAndCategoryNameIn(double startPrice, double endPrice,String categoryType, String[] paramArray, Pageable pageable);

    Page<CatalogEntity> findByProductPriceBetweenAndCategoryType(double startPrice, double endPrice, String categoryType, Pageable pageable);

    Page<CatalogEntity> findByProductNameLikeAndProductPriceBetweenAndCategoryNameIn(String productName, double startPrice, double endPrice, String[] paramArray, Pageable pageable);

    Page<CatalogEntity> findByProductNameLikeAndProductPriceBetween(String productName, double startPrice, double endPrice, Pageable pageable);

    Page<CatalogEntity> findByProductPriceBetweenAndAndCategoryNameIn(double startPrice, double endPrice, String[] paramArray, Pageable pageable);

    Page<CatalogEntity> findByProductPriceBetween(double startPrice, double endPrice, Pageable pageable);


}
