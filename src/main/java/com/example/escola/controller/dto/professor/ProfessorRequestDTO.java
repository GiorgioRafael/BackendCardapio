package com.example.escola.controller.dto.professor;

import com.example.escola.controller.dto.endereco.EnderecoDTO;

import java.time.LocalDate;

public record ProfessorRequestDTO(
        String nomeCompleto,
        String email,
        String cpf,
        String telefoneContato,
        LocalDate dataContratacao,

        //Dados do Endereço (aninhado)
        EnderecoDTO endereco,

        //Dados para criacao do user
        String login,
        String senha

) {}
