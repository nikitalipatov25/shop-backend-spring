package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<Catalog, UUID> {

    Page<Catalog> findByNameLikeAndPriceBetweenAndAnimalAndCategoryIn(String productName, double startPrice, double endPrice, String categoryType, String[] paramArray, Pageable pageable);

    Page<Catalog> findByNameLikeAndPriceBetweenAndAnimal(String productName, double startPrice, double endPrice, String categoryType, Pageable pageable);

    Page<Catalog> findByPriceBetweenAndAnimalAndCategoryIn(double startPrice, double endPrice, String categoryType, String[] paramArray, Pageable pageable);

    Page<Catalog> findByPriceBetweenAndAnimal(double startPrice, double endPrice, String categoryType, Pageable pageable);

    Page<Catalog> findByNameLikeAndPriceBetweenAndCategoryIn(String productName, double startPrice, double endPrice, String[] paramArray, Pageable pageable);

    Page<Catalog> findByNameLikeAndPriceBetween(String productName, double startPrice, double endPrice, Pageable pageable);

    Page<Catalog> findByPriceBetweenAndAndCategoryIn(double startPrice, double endPrice, String[] paramArray, Pageable pageable);

    Page<Catalog> findByPriceBetween(double startPrice, double endPrice, Pageable pageable);

    List<Catalog> findAllByCategory(String category);


}
