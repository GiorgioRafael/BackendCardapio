package com.example.escola.controller.dto.user;

import com.example.escola.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
