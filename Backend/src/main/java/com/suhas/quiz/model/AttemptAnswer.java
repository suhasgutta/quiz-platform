package com.suhas.quiz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attempt_answers")
public class AttemptAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_attempt_id", nullable = false)
    private QuizAttempt quizAttempt;

    public Question getQuestion() {
        return question;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private int selectedIndex;

    private boolean correct;

    public boolean isCorrect() {
        return correct;
    }

    public AttemptAnswer() {}

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public AttemptAnswer(QuizAttempt quizAttempt,
                         Question question,
                         int selectedIndex,
                         boolean correct) {
        this.quizAttempt = quizAttempt;
        this.question = question;
        this.selectedIndex = selectedIndex;
        this.correct = correct;
    }

    public void setQuizAttempt(QuizAttempt quizAttempt) {
        this.quizAttempt = quizAttempt;
    }

    // getters
}