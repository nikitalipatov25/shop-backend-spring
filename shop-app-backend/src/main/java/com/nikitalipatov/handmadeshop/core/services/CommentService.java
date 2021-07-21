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

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");



    @Autowired
    public CommentService(CommentRepository commentRepository, CatalogService catalogService) {
        this.commentRepository = commentRepository;
        this.catalogService = catalogService;
    }

    public List<Comment> findAllComment(UUID uuid){
        var result = commentRepository.findAllByProductId(uuid);
        return result;
    }

    public Comment saveComment(UUID productUUID, Comment comment, HttpServletRequest request){
        Date date = new Date();
        Optional<Catalog> product = catalogService.getById(productUUID);
        Comment newComment = new Comment();
        String header = request.getHeader("Authorization");
        String token = header.substring(7, header.length());
        String username = Jwts.parser().setSigningKey("bezKoderSecretKey").parseClaimsJws(token).getBody().getSubject();
        newComment.setUserName(username);
        newComment.setProductId(product.get().getId());
        newComment.setDate(formatter.format(date));
        newComment.setRating(comment.getRating());
        newComment.setText(comment.getText());
        return commentRepository.save(newComment);
    }

    public Optional<Comment> modifyComment(String username ,Comment comment){
        Optional<Comment> result = commentRepository.findByUserName(username);
        Date date = new Date();
        return result
                .map(entity -> {
                    entity.setText(comment.getText());
                    entity.setRating(comment.getRating());
                    entity.setChangeable(true);
                    entity.setDate(formatter.format(date));
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
