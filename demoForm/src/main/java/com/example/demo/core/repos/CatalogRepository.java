package com.example.demo.core.repos;

import com.example.demo.core.models.CatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CatalogRepository extends JpaRepository<CatalogEntity, UUID> {


    Page<CatalogEntity>  findByProductNameLike(String productName, Pageable pageable);

//    Page<CatalogEntity> findByProductName(String search);
}
