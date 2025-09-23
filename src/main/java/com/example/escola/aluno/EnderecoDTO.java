package com.example.escola.aluno;

public record EnderecoDTO(
        String cep,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado
) {}