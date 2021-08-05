package com.nikitalipatov.handmadeshop.core.repos;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findAllByCommentCommentId(Long id);
    Optional<Answer> findByAnswerId(Long id);
    Optional<Answer> deleteAnswerByAnswerId(Long id);
}
