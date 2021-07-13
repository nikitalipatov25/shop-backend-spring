package com.example.demo.core.repos;

import com.example.demo.core.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface oldUserRepository extends JpaRepository<UserEntity, UUID> {
}
