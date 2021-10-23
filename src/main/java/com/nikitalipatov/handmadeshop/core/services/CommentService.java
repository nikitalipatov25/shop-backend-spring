package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.repositories.AnswerRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final ProductService productService;
    private final UserService userService;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");

    @Autowired
    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository, ProductService productService, UserService userService) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<Comment> findAllComment(UUID uuid){
        var result = commentRepository.findAll();
        return result;
    }

    public Comment saveComment(UUID productUUID, Comment comment, HttpServletRequest request){
        var user = userService.findUser(request);
        Date date = new Date();
        Optional<Product> product = productService.getById(productUUID);
        Comment newComment = new Comment();
        newComment.setDate(LocalDateTime.now());
        newComment.setRating(comment.getRating());
        newComment.setText(comment.getText());
        HashSet<Comment> set = new HashSet<>();
        set.add(newComment);
        product.get().setComments(set);
        newComment.setUserName(user.get().getUsername());
//        productService.editCatalog(productUUID, product.get());
        return commentRepository.save(newComment);
    }

    public Optional<Comment> modifyComment(Long id ,Comment comment){
        Date date = new Date();
        Optional<Comment> result = commentRepository.findById(id);
        return result
                .map(entity -> {
                    entity.setText(comment.getText());
                    entity.setRating(comment.getRating());
                    return commentRepository.save(entity);
                });
    }

    public Optional<Boolean> delComment(Long id){
        Optional<Comment> deletedComment = commentRepository.findById(id);
        return deletedComment
                .map(entity -> {
                   commentRepository.deleteById(id);
                   return true;
                });
    }

    //answers
//    public Optional<Answer> getAnswerByCommentId(Long commentId){
//        var com = commentRepository.findById(commentId);
//        var result = answerRepository.findByComments(com.get());
//        return Optional.ofNullable(result);
//    }

    public Answer saveAnswer(Long commentId, Answer answer, HttpServletRequest request){
        Date date = new Date();
        var user = userService.findUser(request);
        Answer newAnswer = new Answer();
        newAnswer.setText(answer.getText());
        newAnswer.setDate(LocalDateTime.now());
        Optional<Comment> comment = commentRepository.findById(commentId);
        Set<Answer> answerList = new HashSet<>();
        answerList.add(newAnswer);
        //comment.get().setAnswers(answerList);
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
