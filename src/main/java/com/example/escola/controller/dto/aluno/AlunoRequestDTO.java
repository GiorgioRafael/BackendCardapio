package com.example.escola.controller.dto.aluno;

import com.example.escola.controller.dto.endereco.EnderecoDTO;
import com.example.escola.controller.dto.responsavel.ResponsavelDTO;

import java.time.LocalDate;

public record AlunoRequestDTO(
        String nomeCompleto,
        String email,
        String telefoneContato,
        String cpf,
        String rg,
        LocalDate dataNascimento,

        // DTOs aninhados
        EnderecoDTO endereco,
        ResponsavelDTO responsavel
) {}
