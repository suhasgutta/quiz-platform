package com.suhas.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    private int score;

    private int totalQuestions;

    private double percentage;

    private LocalDateTime attemptedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "quizAttempt", cascade = CascadeType.ALL)
    private List<AttemptAnswer> answers;

    public QuizAttempt() {}

    public QuizAttempt(User user, Topic topic, int score, int totalQuestions, double percentage) {
        this.user = user;
        this.topic = topic;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage=percentage;
    }

    public void setAnswers(List<AttemptAnswer> answers) {
        this.answers = answers;

        if (answers != null) {
            for (AttemptAnswer answer : answers) {
                answer.setQuizAttempt(this);
            }
        }
    }
    // getters

}