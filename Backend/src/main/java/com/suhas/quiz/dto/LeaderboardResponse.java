package com.suhas.quiz.dto;

public class LeaderboardResponse {

    private String username;
    private int score;
    private double percentage;

    public LeaderboardResponse(String username, int score, double percentage) {
        this.username = username;
        this.score = score;
        this.percentage = percentage;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public double getPercentage() {
        return percentage;
    }
}