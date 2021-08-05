package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import com.nikitalipatov.handmadeshop.core.models.Catalog;
import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.repos.AnswerRepository;
import com.nikitalipatov.handmadeshop.core.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final CatalogService catalogService;
    private final UserService userService;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");

    @Autowired
    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository,CatalogService catalogService, UserService userService) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.catalogService = catalogService;
        this.userService = userService;
    }

    public List<Comment> findAllComment(UUID uuid){
        var result = commentRepository.findAllByProductId(uuid);
        return result;
    }

    public Comment saveComment(UUID productUUID, Comment comment, HttpServletRequest request){
        Date date = new Date();
        Optional<Catalog> product = catalogService.getById(productUUID);
        Comment newComment = new Comment();
        var user = userService.findUser(request);
        newComment.setUserName(user.get().getUsername());
        newComment.setProductId(product.get().getId());
        newComment.setDate(formatter.format(date));
        newComment.setRating(comment.getRating());
        newComment.setText(comment.getText());
        return commentRepository.save(newComment);
    }

    public Optional<Comment> modifyComment(Long id ,Comment comment){
        Date date = new Date();
        Optional<Comment> result = commentRepository.findByCommentId(id);
        return result
                .map(entity -> {
                    entity.setText(comment.getText());
                    entity.setRating(comment.getRating());
                    entity.setDateUpdate(formatter.format(date));
                    return commentRepository.save(entity);
                });
    }

    public Optional<Boolean> delComment(Long id){
        Optional<Comment> deletedComment = commentRepository.findByCommentId(id);
        return deletedComment
                .map(entity -> {
                   commentRepository.deleteByCommentId(id);
                   return true;
                });
    }

    //answers
    public Optional<Answer> getAnswerByCommentId(Long commentId){
        var result = answerRepository.findAllByCommentCommentId(commentId);
        return result;
    }

    public Answer saveAnswer(Long commentId, Answer answer, HttpServletRequest request){
        Date date = new Date();
        var user = userService.findUser(request);
        Answer newAnswer = new Answer();
        newAnswer.setUserName(user.get().getUsername());
        newAnswer.setText(answer.getText());
        newAnswer.setDate(formatter.format(date));
        Optional<Comment> comment = commentRepository.findByCommentId(commentId);
        List<Answer> answerList = new ArrayList<>();
        answerList.add(newAnswer);
        comment.get().setAnswers(answerList);
        return answerRepository.save(newAnswer);
    }
//
//    public Optional<Boolean> delAnswer(Long id){
//        Optional<Answer> deleteAnswer = answerRepository.findByAnswerId(id);
//        return deleteAnswer
//                .map(entity -> {
//                    answerRepository.deleteAnswerByAnswerId(id);
//                    return true;
//                });
//    }

}
