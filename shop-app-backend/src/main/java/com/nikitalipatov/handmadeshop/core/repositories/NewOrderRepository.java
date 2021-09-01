package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.NewOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NewOrderRepository extends JpaRepository<NewOrder, UUID> {
}
