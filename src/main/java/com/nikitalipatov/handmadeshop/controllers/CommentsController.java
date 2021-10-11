package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import com.nikitalipatov.handmadeshop.core.models.Comment;
import com.nikitalipatov.handmadeshop.core.models.Comments;
import com.nikitalipatov.handmadeshop.core.services.CommentService;
import com.nikitalipatov.handmadeshop.core.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping(value = "/newcomments")
public class CommentsController {
        private final CommentsService commentsService;

        @Autowired
        public CommentsController(CommentsService commentsService) {
                this.commentsService = commentsService;
        }

        @GetMapping("/{id}")
        public ResponseEntity<Page<Comments>> findAllComment(@PathVariable("id") UUID productId){
                var result = commentsService.findAllComment(productId);
                return ResponseEntity.ok(result);
        }

        @PostMapping("/{id}")
        public ResponseEntity<Comments> addComment(@PathVariable(name = "id")UUID productId, @RequestBody Comments comment, HttpServletRequest request){
                Comments createComment = commentsService.saveComment(productId, comment, request);
                return ResponseEntity.ok(createComment);
        }

        @PutMapping(value = "/{id}")
        public ResponseEntity<Comments> modifyComment(@PathVariable(name = "id")UUID productId, @RequestBody Comments comment, HttpServletRequest request){
                Optional<Comments> result = commentsService.modifyComment(productId, comment, request);
                return result
                        .map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteComment(@PathVariable(name = "id")UUID productId, HttpServletRequest request){
                Optional<Boolean> deletedComment = commentsService.delComment(productId, request);
                return deletedComment
                        .map(e -> ResponseEntity.noContent().build())
                        .orElseGet(() -> ResponseEntity.notFound().build());
        }
}
