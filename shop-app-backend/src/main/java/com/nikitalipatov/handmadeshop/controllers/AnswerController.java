package com.nikitalipatov.handmadeshop.controllers;

import com.nikitalipatov.handmadeshop.core.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping(value = "/answer")
public class AnswerController {

    AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
}
