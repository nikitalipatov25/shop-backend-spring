package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.models.Comments;
import com.nikitalipatov.handmadeshop.core.models.Product;
import com.nikitalipatov.handmadeshop.core.repositories.AnswerRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CommentRepository;
import com.nikitalipatov.handmadeshop.core.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final ProductService productService;
    private final UserService userService;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, ProductService productService, UserService userService) {
        this.commentsRepository = commentsRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<Comments> findAllComment(UUID uuid){
        var result = commentsRepository.findAll();
        return result;
    }

    public Comments saveComment(UUID productUUID, Comments comment, HttpServletRequest request){
        var user = userService.findUser(request);
//        Optional<Product> product = productService.getById(productUUID);
        Comments newComment = new Comments();
        newComment.setProductId(productUUID);
        newComment.setUserId(user.get().getId());
        newComment.setDate(LocalDateTime.now());
        newComment.setRating(comment.getRating());
        newComment.setText(comment.getText());
//        HashSet<Comment> set = new HashSet<>();
//        set.add(newComment);
//        product.get().setComments(set);
        //newComment.setUserName(user.get().getUsername());
//        productService.editCatalog(productUUID, product.get());
        return commentsRepository.save(newComment);
    }

    public Optional<Comments> modifyComment(UUID productId ,Comments comment, HttpServletRequest request){
        //Date date = new Date();
        var user = userService.findUser(request);
        Optional<Comments> result = commentsRepository.findByProductIdAndUserId(productId, user.get().getId());
        return result
                .map(entity -> {
                    entity.setText(comment.getText());
                    entity.setRating(comment.getRating());
                    return commentsRepository.save(entity);
                });
    }

    public Optional<Boolean> delComment(UUID productId, HttpServletRequest request){
        var user = userService.findUser(request);
        Optional<Comments> deletedComment = commentsRepository.findByProductIdAndUserId(productId, user.get().getId());
        return deletedComment
                .map(entity -> {
                    commentsRepository.deleteById(id);
                    return true;
                });
    }
}
