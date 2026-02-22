package com.suhas.quiz.service.impl;

import com.suhas.quiz.dto.LoginRequest;
import com.suhas.quiz.dto.RegisterRequest;
import com.suhas.quiz.model.Role;
import com.suhas.quiz.model.User;
import com.suhas.quiz.repository.UserRepository;
import com.suhas.quiz.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.suhas.quiz.security.JwtUtil;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public void register(RegisterRequest request) {

        User user = new User(
                request.username,
                request.email,
                passwordEncoder.encode(request.password),
                Role.ROLE_USER
        );

        userRepository.save(user);
    }

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}