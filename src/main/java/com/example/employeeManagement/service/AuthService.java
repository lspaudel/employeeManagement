package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.LoginResult;
import com.example.employeeManagement.exception.ConflictException;
import com.example.employeeManagement.model.User;
import com.example.employeeManagement.repository.UserRepository;
import com.example.employeeManagement.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public LoginResult login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

       String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
       return new LoginResult(token, jwtUtils.getExpirationTime(), user);
    }

    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ConflictException("User already exists: " + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
