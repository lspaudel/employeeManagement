package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.AuthResponse;
import com.example.employeeManagement.dto.LoginResult;
import com.example.employeeManagement.dto.UserSummary;
import com.example.employeeManagement.model.User;
import com.example.employeeManagement.service.AuthService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        LoginResult result = authService.login(
                request.getUsername(),
                request.getPassword()
        );
        AuthResponse response = new AuthResponse(
                result.getAccessToken(),
                "Bearer",
                result.getExpiresIn(),
                new UserSummary(
                        result.getUser().getId(),
                        result.getUser().getUsername(),
                        result.getUser().getRole()
                )
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserSummary> register(@RequestBody User user) {
        User savedUser = authService.register(user);
        UserSummary summary = new UserSummary(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole()
        );
      return ResponseEntity.status(HttpStatus.CREATED).body(summary);
    }
}

@Data
class LoginRequest {
    private String username;
    private String password;
}
