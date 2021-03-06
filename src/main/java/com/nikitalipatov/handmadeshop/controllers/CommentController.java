package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Answer;
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

    //answer
//    @GetMapping("/{id}/answer")
//    public ResponseEntity<Optional<Answer>> findAnswer(@PathVariable(name = "id")Long id){
//        var result = commentService.getAnswerByCommentId(id);
//        return ResponseEntity.ok(result);
//    }

    @PostMapping("/{id}/answer")
    public ResponseEntity<Answer> addAnswer(@PathVariable(name = "id")Long id, @RequestBody Answer answer, HttpServletRequest request){
        Answer createAnswer = commentService.saveAnswer(id, answer, request);
        return ResponseEntity.ok(createAnswer);
    }

//    @DeleteMapping("/{id}/answer")
//    public ResponseEntity<?> delAnswer(@PathVariable(name = "id")Long id){
//        Optional<Boolean> deleteAnswer = commentService.delAnswer(id);
//        return deleteAnswer
//                .map(e -> ResponseEntity.noContent().build())
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }


}
