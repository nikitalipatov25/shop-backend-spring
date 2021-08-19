package com.nikitalipatov.handmadeshop.core.repositories;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import com.nikitalipatov.handmadeshop.core.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    <Set>Answer findByComment(Comment comment);
}
