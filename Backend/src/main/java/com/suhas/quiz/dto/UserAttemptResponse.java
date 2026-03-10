package com.suhas.quiz.dto;

import java.time.LocalDateTime;

public class UserAttemptResponse {

    private String topic;
    private int score;
    private int totalQuestions;
    private double percentage;
    private LocalDateTime attemptedAt;

    public UserAttemptResponse(String topic,
                               int score,
                               int totalQuestions,
                               double percentage,
                               LocalDateTime attemptedAt) {

        this.topic = topic;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.attemptedAt = attemptedAt;
    }

    public String getTopic() {
        return topic;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }
}