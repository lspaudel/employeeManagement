package com.example.employeeManagement.dto;

import com.example.employeeManagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResult {

    private String accessToken;
    private Long expiresIn;
    private User user;
}
