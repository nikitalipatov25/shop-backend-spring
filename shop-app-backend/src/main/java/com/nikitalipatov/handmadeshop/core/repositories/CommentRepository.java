package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByProductId(UUID uuid);
    Optional<Comment> findByUserName(String username);
    Optional<Comment> findByCommentId(Long id);
    Optional<Comment> deleteByCommentId(Long id);
}