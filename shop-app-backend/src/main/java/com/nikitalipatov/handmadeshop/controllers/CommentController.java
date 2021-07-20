package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    /*
    37fd1a62-f27b-4c5c-b259-47916521c44a Stas
    416b0124-dafb-4de0-826d-3ba1ad9fb025 NewUser eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOZXdVc2VyIiwiaWF0IjoxNjI2NzcyMDE5LCJleHAiOjE2MjY4NTg0MTl9.pP3KjVUqbf_Qqn8phlU7Uc9IDDnYq_g6SbP6E_gBA34RJg3n-9Xk4WDyBAbyYRzP1apL9zRXCAfy6m5DbYOrNQ


     */

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> findAllComment(@PathVariable("id")UUID productId){
        var result = commentService.findAllComment(productId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Comment> addComment(@PathVariable(name = "id")UUID productId, @RequestBody Comment comment, HttpServletRequest request){
        Comment createComment = commentService.saveComment(productId, comment, request);
        return ResponseEntity.ok(createComment);
    }

    @PutMapping(value = "/{userName}")
    public ResponseEntity<Comment> modifyComment(@PathVariable(name = "userName")String username, @RequestBody Comment comment){
        Optional<Comment> result = commentService.modifyComment(username, comment);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("del/{userName}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "userName")String username){
        Optional<Boolean> deletedComment = commentService.delComment(username);
        return deletedComment
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
