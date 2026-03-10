package com.suhas.quiz.dto;

import java.util.List;

public class QuizResultResponse {

    public int score;
    public int totalQuestions;
    public double percentage;
    private List<AnswerReview> answers;

    public QuizResultResponse(int score, int totalQuestions,
                              double percentage,
                              List<AnswerReview> answers) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.answers = answers;
    }
}