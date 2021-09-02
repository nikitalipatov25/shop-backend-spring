package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.NewOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NewOrderRepository extends JpaRepository<NewOrder, UUID> {
    Page<NewOrder> findAllByUserId(Long userId, Pageable pageable);
    Optional<NewOrder> findByOrderId(UUID orderId);
}
