package com.suhas.quiz.controller;

import com.suhas.quiz.dto.CreateQuestionRequest;
import com.suhas.quiz.service.QuestionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createQuestion(@RequestBody CreateQuestionRequest request) {
        questionService.createQuestion(request);
        return "Question created successfully";
    }
}