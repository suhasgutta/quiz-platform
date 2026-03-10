package com.suhas.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String questionText;

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    @ElementCollection
    @CollectionTable(name = "question_options",
            joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_value")
    private List<String> options;

    @Column(nullable = false)
    private int correctAnswerIndex;

    public Topic getTopic() {
        return topic;
    }

    public List<String> getOptions() {
        return options;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Question() {}

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Question(String questionText,
                    List<String> options,
                    int correctAnswerIndex,
                    Topic topic) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.topic = topic;
    }

    // getters
}