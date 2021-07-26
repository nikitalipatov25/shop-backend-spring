package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600,
        allowedHeaders = {
                "*"
        },
        methods = { RequestMethod.POST,RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT }
)
@RestController
@Transactional
@RequestMapping(value = "/comments")
public class CommentController {

    /*
    37fd1a62-f27b-4c5c-b259-47916521c44a Stas
    416b0124-dafb-4de0-826d-3ba1ad9fb025 NewUser eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOZXdVc2VyIiwiaWF0IjoxNjI2NzcyMDE5LCJleHAiOjE2MjY4NTg0MTl9.pP3KjVUqbf_Qqn8phlU7Uc9IDDnYq_g6SbP6E_gBA34RJg3n-9Xk4WDyBAbyYRzP1apL9zRXCAfy6m5DbYOrNQ
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOZXdVc2VyIiwiaWF0IjoxNjI2OTQxNDE1LCJleHAiOjE2MjcwMjc4MTV9.TvrEKAJqleXWoPoBnQwuw2RbqW5WqvmKgH117W5PMRk9W7zLrOgUhcqDbtjEHlvtW8PpzzTSntkPWNKww9F9sg

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

    @PostMapping("/{id}")
    public ResponseEntity<Comment> addComment(@PathVariable(name = "id")UUID productId, @RequestBody Comment comment, HttpServletRequest request){
        Comment createComment = commentService.saveComment(productId, comment, request);
        return ResponseEntity.ok(createComment);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Comment> modifyComment(@PathVariable(name = "id")Long id, @RequestBody Comment comment){
        Optional<Comment> result = commentService.modifyComment(id, comment);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id")Long id){
        Optional<Boolean> deletedComment = commentService.delComment(id);
        return deletedComment
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
