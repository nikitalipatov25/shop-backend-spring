package com.example.demo.core.repos;

import com.example.demo.core.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {

}
