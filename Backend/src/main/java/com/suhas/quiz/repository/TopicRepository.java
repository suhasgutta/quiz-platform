package com.suhas.quiz.repository;

import com.suhas.quiz.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}