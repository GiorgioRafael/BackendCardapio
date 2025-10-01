package com.example.escola.controller.dto.pessoa;

import com.example.escola.controller.dto.endereco.EnderecoDTO;
import com.example.escola.dal.entities.Pessoa;

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
) {
    public PessoaResponseDTO(Pessoa pessoa) {
        this(
                pessoa.getId(),
                pessoa.getNomeCompleto(),
                pessoa.getEmail(),
                pessoa.getCpf(),
                pessoa.getRg(),
                pessoa.getDataNascimento(),
                pessoa.getTelefoneContato(),
                pessoa.getEndereco() != null ? new EnderecoDTO(
                        pessoa.getEndereco().getLogradouro(),
                        pessoa.getEndereco().getNumero(),
                        pessoa.getEndereco().getBairro(),
                        pessoa.getEndereco().getCidade(),
                        pessoa.getEndereco().getEstado(),
                        pessoa.getEndereco().getCep()
                ) : null
        );
    }
}
