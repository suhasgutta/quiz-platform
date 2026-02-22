package com.suhas.quiz.service;

import com.suhas.quiz.dto.LoginRequest;
import com.suhas.quiz.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

    String login(LoginRequest request);
}