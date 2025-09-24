package com.example.escola.dto.aluno;

public record EnderecoDTO(
        String cep,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado
) {}