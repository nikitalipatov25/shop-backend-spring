package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.models.Comments;
import com.nikitalipatov.handmadeshop.core.repositories.AnswerRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CommentRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CommentsRepository;
import com.nikitalipatov.handmadeshop.dto.AnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Service
public class AnswerService {

    private final CommentsRepository commentsRepository;
    private final AnswerRepository answerRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AnswerService(CommentsRepository commentsRepository, AnswerRepository answerRepository, ProductService productService, UserService userService) {
        this.commentsRepository = commentsRepository;
        this.answerRepository = answerRepository;
        this.productService = productService;
        this.userService = userService;
    }

//    public Optional<Answer> getAnswerByCommentId(Long commentId){
//        var com = commentRepository.findById(commentId);
//        var result = answerRepository.findByComment(com.get());
//        return Optional.ofNullable(result);
//    }

    public Answer saveAnswer(AnswerDTO answerDTO, HttpServletRequest request){
        //Date date = new Date();
        var user = userService.findUser(request);
        Answer newAnswer = new Answer();
        newAnswer.setText(answerDTO.getText());
        newAnswer.setDate(LocalDateTime.now());
        Optional<Comments> comment = commentsRepository.findByProductIdAndUserId(answerDTO.getProductId(),
                userService.findUserByUserName(answerDTO.getAnswerToUser()).get().getId());
        Set<Answer> answerList = new HashSet<>();
        answerList.add(newAnswer);
        //comment.get().setAnswers(answerList);
        return answerRepository.save(newAnswer);
    }

    public Optional<Boolean> delAnswer(Long id){
        Optional<Answer> deleteAnswer = answerRepository.findById(id);
        return deleteAnswer
                .map(entity -> {
                    answerRepository.deleteById(id);
                    return true;
                });
    }

    public Optional<Answer> modifyAnswer(Long id, Comments comment, HttpServletRequest request) {
        var user = userService.findUser(request);
        Optional<Answer> result = answerRepository.findById(id);
        return result
                .map(entity -> {
                    entity.setText(comment.getText());
                    entity.setDate(LocalDateTime.now());
                    return answerRepository.save(entity);
                });
    }
}
