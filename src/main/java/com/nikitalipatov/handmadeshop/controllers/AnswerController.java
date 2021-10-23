package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.models.Answer;
import com.nikitalipatov.handmadeshop.core.models.Comments;
import com.nikitalipatov.handmadeshop.core.services.AnswerService;
import com.nikitalipatov.handmadeshop.core.services.CommentsService;
import com.nikitalipatov.handmadeshop.dto.AnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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
@RequestMapping(value = "/newanswer")
public class AnswerController {

    private final AnswerService answerService;
    private final CommentsService commentsService;

    @Autowired
    public AnswerController(AnswerService answerService, CommentsService commentsService) {
        this.answerService = answerService;
        this.commentsService = commentsService;
    }

//    @GetMapping("/answer/{id}")
//    public ResponseEntity<Optional<Answer>> findAnswer(@PathVariable(name = "id")Long id){
//        var result = answerService.getAnswerByCommentId(id);
//        return ResponseEntity.ok(result);
//    }

    @PostMapping()
    public ResponseEntity<Answer> addAnswer(@RequestBody AnswerDTO answerDTO, HttpServletRequest request){
        Answer createAnswer = answerService.saveAnswer(answerDTO, request);
        answerService.setAnswerToComment(createAnswer, answerDTO);
        return ResponseEntity.ok(createAnswer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delAnswer(@PathVariable(name = "id")Long id){
        Optional<Boolean> deleteAnswer = answerService.delAnswer(id);
        return deleteAnswer
                .map(e -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Answer> modifyComment(@PathVariable(name = "id") Long id, @RequestBody Comments comment, HttpServletRequest request){
        Optional<Answer> result = answerService.modifyAnswer(id, comment, request);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
