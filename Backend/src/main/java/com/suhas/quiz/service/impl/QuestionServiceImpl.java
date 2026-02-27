package com.suhas.quiz.service.impl;

import com.suhas.quiz.dto.CreateQuestionRequest;
import com.suhas.quiz.model.Question;
import com.suhas.quiz.model.Topic;
import com.suhas.quiz.repository.QuestionRepository;
import com.suhas.quiz.repository.TopicRepository;
import com.suhas.quiz.service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TopicRepository topicRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void createQuestion(CreateQuestionRequest request) {

        // 🔥 Validate topic
        Topic topic = topicRepository.findById(request.topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        // 🔥 Basic validation
        if (request.questionText == null || request.questionText.trim().isEmpty()) {
            throw new RuntimeException("Question cannot be empty");
        }

        if (request.options == null || request.options.size() < 2) {
            throw new RuntimeException("At least two options required");
        }

        if (request.correctAnswerIndex < 0 ||
                request.correctAnswerIndex >= request.options.size()) {
            throw new RuntimeException("Invalid correct answer index");
        }

        Question question = new Question(
                request.questionText,
                request.options,
                request.correctAnswerIndex,
                topic
        );

        questionRepository.save(question);
    }
}