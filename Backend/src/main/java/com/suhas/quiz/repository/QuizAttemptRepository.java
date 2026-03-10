package com.suhas.quiz.repository;

import com.suhas.quiz.dto.LeaderboardResponse;
import com.suhas.quiz.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

import java.util.List;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    @Query("""
SELECT new com.suhas.quiz.dto.LeaderboardResponse(
    qa.user.username,
    qa.score,
    qa.percentage
)
FROM QuizAttempt qa
WHERE qa.topic.id = :topicId
ORDER BY qa.percentage DESC
""")
    List<LeaderboardResponse> getLeaderboard(Long topicId);

    Optional<QuizAttempt> findByUserIdAndTopicId(Long userId, Long topicId);
}