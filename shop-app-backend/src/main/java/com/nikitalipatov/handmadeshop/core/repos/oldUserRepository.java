package com.nikitalipatov.handmadeshop.core.repos;

import com.nikitalipatov.handmadeshop.core.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface oldUserRepository extends JpaRepository<UserEntity, UUID> {
}
