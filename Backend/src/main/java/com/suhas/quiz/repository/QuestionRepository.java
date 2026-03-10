package com.suhas.quiz.repository;

import com.suhas.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = """
            SELECT * FROM questions
            WHERE topic_id = :topicId
            ORDER BY RANDOM()
            LIMIT :limit
            """, nativeQuery = true)
    List<Question> findRandomQuestionsByTopic(
            @Param("topicId") Long topicId,
            @Param("limit") int limit
    );
}