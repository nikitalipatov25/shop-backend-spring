package com.nikitalipatov.handmadeshop.core.services;

import com.nikitalipatov.handmadeshop.core.models.Catalog;
import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.repos.CatalogRepository;
import com.nikitalipatov.handmadeshop.core.repos.CommentRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CatalogService catalogService;
    private final UserService userService;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");

    @Autowired
    public CommentService(CommentRepository commentRepository, CatalogService catalogService, UserService userService) {
        this.commentRepository = commentRepository;
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

    public Optional<Comment> modifyComment(Long id ,Comment comment, HttpServletRequest request){
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

}
