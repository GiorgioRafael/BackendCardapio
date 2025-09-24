package com.example.escola.dto.aluno;

import java.time.LocalDate;

//DATA TRANSFER OBJECT DO ALUNO, aninhado em partes (colocado no mesmo arquivo por conveniencia)
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
