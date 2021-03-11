package com.example.demo.core.repos;

import com.example.demo.core.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    Page<CartEntity> findByCatalogProductNameLike(String filter, Pageable pageable);
}
