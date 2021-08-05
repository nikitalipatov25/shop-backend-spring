package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Page<Cart> findByCatalogProductNameLike(String filter, Pageable pageable);
    Optional<Cart> findByProductId(UUID uuid);
    Optional<Cart> deleteByProductId(UUID uuid);
    List<Cart> findAllByUserName(String username);
    Page<Cart> findAllByUserName(String username, Pageable pageable);
    Optional<Cart> deleteAllByUserName(String username);

}
