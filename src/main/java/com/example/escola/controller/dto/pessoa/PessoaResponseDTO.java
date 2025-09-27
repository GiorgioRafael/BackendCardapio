package com.example.escola.controller.dto.pessoa;

import com.example.escola.controller.dto.endereco.EnderecoDTO;

import java.time.LocalDate;

public record PessoaResponseDTO(
        String id,
        String nomeCompleto,
        String email,
        String cpf,
        String rg,
        LocalDate dataNascimento,
        String telefoneContato,
        EnderecoDTO endereco
) {}
