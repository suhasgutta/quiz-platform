package com.suhas.quiz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin access granted";
    }
}