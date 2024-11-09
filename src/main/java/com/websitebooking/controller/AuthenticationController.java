package com.websitebooking.controller;

import com.websitebooking.model.User;
import com.websitebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.findByUsername(user.getUsername())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> "Login successful, Role: " + u.getRole())
                .orElse("Invalid credentials");
    }

    @PostMapping("/promote/admin/{userId}")
    public String promoteToAdmin(@PathVariable Long userId) {
        userService.promoteToAdmin(userId);
        return "User promoted to Admin";
    }

    @PostMapping("/promote/administrator/{userId}")
    public String promoteToSuperAdmin(@PathVariable Long userId) {
        userService.promoteToSuperAdmin(userId);
        return "User promoted to Super Admin";
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }
}
