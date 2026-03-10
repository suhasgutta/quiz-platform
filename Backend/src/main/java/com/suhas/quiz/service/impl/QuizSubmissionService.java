package com.suhas.quiz.service.impl;

import com.suhas.quiz.dto.*;
import com.suhas.quiz.model.*;
import com.suhas.quiz.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.*;

@Service
public class QuizSubmissionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    public QuizSubmissionService(QuestionRepository questionRepository,
                                 TopicRepository topicRepository,
                                 UserRepository userRepository,
                                 QuizAttemptRepository quizAttemptRepository) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.quizAttemptRepository = quizAttemptRepository;
    }

    public QuizResultResponse submitQuiz(SubmitQuizRequest request) {

        // 1️ Validate topic
        Topic topic = topicRepository.findById(request.topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));



        // 2️ Get logged-in user
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<QuizAttempt> existingAttempt =
                quizAttemptRepository.findByUserIdAndTopicId(user.getId(), topic.getId());

        if (existingAttempt.isPresent()) {
            throw new RuntimeException("You have already attempted this quiz");
        }

        if (request.answers == null || request.answers.isEmpty()) {
            throw new RuntimeException("Answers cannot be empty");
        }

        // 3️ Prevent duplicate question submission
        Set<Long> uniqueQuestionIds = new HashSet<>();
        for (SubmitAnswer ans : request.answers) {
            if (!uniqueQuestionIds.add(ans.questionId)) {
                throw new RuntimeException("Duplicate question submitted");
            }
        }

        int score = 0;

        List<AttemptAnswer> attemptAnswers = new ArrayList<>();
        List<AnswerReview> reviews = new ArrayList<>();

        // 4️ Process answers
        for (SubmitAnswer submitted : request.answers) {

            Question question = questionRepository.findById(submitted.questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            // Validate topic
            if (!question.getTopic().getId().equals(request.topicId)) {
                throw new RuntimeException("Question does not belong to topic");
            }

            // Validate selected index
            if (submitted.selectedIndex < 0 ||
                    submitted.selectedIndex >= question.getOptions().size()) {
                throw new RuntimeException("Invalid answer index");
            }

            boolean isCorrect =
                    submitted.selectedIndex == question.getCorrectAnswerIndex();

            if (isCorrect) {
                score++;
            }

            // Store attempt answer
            AttemptAnswer attemptAnswer =
                    new AttemptAnswer(null, question,
                            submitted.selectedIndex, isCorrect);

            attemptAnswers.add(attemptAnswer);

            // Build answer review
            String correctAnswer =
                    question.getOptions().get(question.getCorrectAnswerIndex());

            String yourAnswer =
                    question.getOptions().get(submitted.selectedIndex);

            reviews.add(
                    new AnswerReview(
                            question.getQuestionText(),
                            yourAnswer,
                            correctAnswer,
                            isCorrect
                    )
            );
        }

        int totalQuestions = request.answers.size();
        double percentage = ((double) score / totalQuestions) * 100;

        // 5⃣ Create attempt
        QuizAttempt attempt =
                new QuizAttempt(user, topic, score, totalQuestions, percentage);

        // 6️ Link attempt answers
        for (AttemptAnswer answer : attemptAnswers) {
            answer.setQuizAttempt(attempt);
        }

        attempt.setAnswers(attemptAnswers);

        // 7️ Save attempt
        quizAttemptRepository.save(attempt);

        // 8️ Return result with review
        return new QuizResultResponse(score, totalQuestions, percentage, reviews);
    }
}