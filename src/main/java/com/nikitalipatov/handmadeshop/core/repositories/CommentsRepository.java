package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comments, UUID> {
    Optional<Comments> findByProductIdAndUserId(UUID productId, Long userId);

}
