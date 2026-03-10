package com.suhas.quiz.dto;

public class AnswerReview {

    private String question;
    private String yourAnswer;
    private String correctAnswer;
    private boolean correct;

    public AnswerReview(String question, String yourAnswer,
                        String correctAnswer, boolean correct) {
        this.question = question;
        this.yourAnswer = yourAnswer;
        this.correctAnswer = correctAnswer;
        this.correct = correct;
    }

    public String getQuestion() { return question; }
    public String getYourAnswer() { return yourAnswer; }
    public String getCorrectAnswer() { return correctAnswer; }
    public boolean isCorrect() { return correct; }
}