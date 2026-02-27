package com.suhas.quiz.service;

import com.suhas.quiz.dto.CreateQuestionRequest;

public interface QuestionService {
    void createQuestion(CreateQuestionRequest request);
}