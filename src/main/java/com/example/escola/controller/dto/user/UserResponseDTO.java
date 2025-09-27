package com.example.escola.controller.dto.user;

import com.example.escola.dal.entities.Pessoa;
import com.example.escola.enums.UserRole;

public record UserResponseDTO(
        String login,
        String password,
        UserRole role,

        //DTO Aninhado
        Pessoa pessoa
) {
}
