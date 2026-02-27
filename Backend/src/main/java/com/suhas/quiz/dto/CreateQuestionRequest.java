package com.suhas.quiz.dto;

import java.util.List;

public class CreateQuestionRequest {
    public String questionText;
    public List<String> options;
    public int correctAnswerIndex;
    public Long topicId;
}