package com.suhas.quiz.controller;

import com.suhas.quiz.dto.*;
import com.suhas.quiz.model.Question;
import com.suhas.quiz.repository.QuestionRepository;
import com.suhas.quiz.service.LeaderboardService;
import com.suhas.quiz.service.UserAttemptService;
import com.suhas.quiz.service.impl.QuizSubmissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserQuizController {

    private final QuestionRepository questionRepository;
    private final QuizSubmissionService quizSubmissionService;
    private final LeaderboardService leaderboardService;
    private final UserAttemptService userAttemptService;

    public UserQuizController(
            QuestionRepository questionRepository,
            QuizSubmissionService quizSubmissionService,
            LeaderboardService leaderboardService,UserAttemptService userAttemptService) {

        this.questionRepository = questionRepository;
        this.quizSubmissionService = quizSubmissionService;
        this.leaderboardService = leaderboardService;
        this.userAttemptService=userAttemptService;
    }

    @GetMapping("/topics/{topicId}/questions")
    public List<QuestionResponse> getQuestions(
            @PathVariable Long topicId,
            @RequestParam(defaultValue = "5") int limit
    ) {

        List<Question> questions =
                questionRepository.findRandomQuestionsByTopic(topicId, limit);

        return questions.stream()
                .map(q -> new QuestionResponse(
                        q.getId(),
                        q.getQuestionText(),
                        q.getOptions()
                ))
                .collect(Collectors.toList());
    }

    @PostMapping("/quiz/submit")
    public QuizResultResponse submitQuiz(
            @RequestBody SubmitQuizRequest request) {
        return quizSubmissionService.submitQuiz(request);
    }

    @GetMapping("/topics/{topicId}/leaderboard")
    public List<LeaderboardResponse> getLeaderboard(
            @PathVariable Long topicId) {
        return leaderboardService.getLeaderboard(topicId);
    }

    @GetMapping("/attempts")
    public List<UserAttemptResponse> getUserAttempts() {
        return userAttemptService.getUserAttempts();
    }
}