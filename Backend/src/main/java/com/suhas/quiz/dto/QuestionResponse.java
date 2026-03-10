package com.suhas.quiz.dto;

import java.util.List;

public class QuestionResponse {

    public Long questionId;
    public String questionText;
    public List<String> options;

    public QuestionResponse(Long questionId,
                            String questionText,
                            List<String> options) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
    }
}