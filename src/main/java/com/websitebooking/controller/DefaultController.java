package com.websitebooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/auth/home"; // Chuyển hướng đến /api/auth/home
    }
}

