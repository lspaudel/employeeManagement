package com.example.employeeManagement.controller;

import com.example.employeeManagement.model.User;
import com.example.employeeManagement.service.AuthService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }
}

@Data
class LoginRequest {
    private String username;
    private String password;
}
