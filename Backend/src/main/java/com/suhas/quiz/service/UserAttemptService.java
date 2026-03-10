package com.suhas.quiz.service;

import com.suhas.quiz.dto.UserAttemptResponse;
import com.suhas.quiz.model.QuizAttempt;
import com.suhas.quiz.model.User;
import com.suhas.quiz.repository.QuizAttemptRepository;
import com.suhas.quiz.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final UserRepository userRepository;

    public UserAttemptService(QuizAttemptRepository quizAttemptRepository,
                              UserRepository userRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
        this.userRepository = userRepository;
    }

    public List<UserAttemptResponse> getUserAttempts() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<QuizAttempt> attempts =
                quizAttemptRepository.findByUserIdOrderByAttemptedAtDesc(user.getId());

        return attempts.stream()
                .map(a -> new UserAttemptResponse(
                        a.getTopic().getName(),
                        a.getScore(),
                        a.getTotalQuestions(),
                        a.getPercentage(),
                        a.getAttemptedAt()
                ))
                .collect(Collectors.toList());
    }
}