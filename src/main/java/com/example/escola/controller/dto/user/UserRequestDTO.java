package com.example.escola.controller.dto.user;

import com.example.escola.dal.entities.Pessoa;
import com.example.escola.enums.UserRole;

public record UserRequestDTO (
        String login,
        String passowrd,
        UserRole role,

        //DTO ANINHADO
        Pessoa pessoa
) {

}
