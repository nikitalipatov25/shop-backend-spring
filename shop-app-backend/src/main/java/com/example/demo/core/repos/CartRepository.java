package com.example.demo.core.repos;

import com.example.demo.core.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {
    Page<CartEntity> findByCatalogProductNameLike(String filter, Pageable pageable);
    Optional<CartEntity> findByProductId(UUID uuid);
    Optional<CartEntity> deleteByProductId(UUID uuid);
    List<CartEntity> findAllByUserName(String username);
    Page<CartEntity> findAllByUserName(String username, Pageable pageable);
    Optional<CartEntity> deleteAllByUserName(String username);

}
