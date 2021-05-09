package com.example.demo.core.repos;

import com.example.demo.core.models.OrdersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrdersRepository extends JpaRepository<OrdersEntity, UUID> {
    List<OrdersEntity> findByUserId(UUID userId);
}
