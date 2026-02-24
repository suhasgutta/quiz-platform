package com.suhas.quiz.controller;

import com.suhas.quiz.dto.CreateTopicRequest;
import com.suhas.quiz.service.TopicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createTopic(@RequestBody CreateTopicRequest request) {
        topicService.createTopic(request);
        return "Topic created successfully";
    }
}