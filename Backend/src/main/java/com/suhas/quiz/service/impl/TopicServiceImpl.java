package com.suhas.quiz.service.impl;

import com.suhas.quiz.dto.CreateTopicRequest;
import com.suhas.quiz.model.Topic;
import com.suhas.quiz.model.User;
import com.suhas.quiz.repository.TopicRepository;
import com.suhas.quiz.repository.UserRepository;
import com.suhas.quiz.service.TopicService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public TopicServiceImpl(TopicRepository topicRepository,
                            UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createTopic(CreateTopicRequest request) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User admin = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Topic topic = new Topic(
                request.name,
                request.description,
                admin
        );

        topicRepository.save(topic);
    }
}