package com.suhas.quiz.service;

import com.suhas.quiz.dto.LeaderboardResponse;
import com.suhas.quiz.repository.QuizAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    private final QuizAttemptRepository quizAttemptRepository;

    public LeaderboardService(QuizAttemptRepository quizAttemptRepository) {
        this.quizAttemptRepository = quizAttemptRepository;
    }

    public List<LeaderboardResponse> getLeaderboard(Long topicId) {
        return quizAttemptRepository.getLeaderboard(topicId);
    }
}