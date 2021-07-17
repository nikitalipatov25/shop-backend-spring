package com.nikitalipatov.handmadeshop.core.repos;

import com.nikitalipatov.handmadeshop.core.models.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {
    List<OrdersEntity> findByUserId(UUID userId);
}
