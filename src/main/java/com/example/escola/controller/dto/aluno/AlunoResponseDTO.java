package com.example.escola.controller.dto.aluno;

import com.example.escola.dal.entities.Aluno;
import com.example.escola.controller.dto.endereco.EnderecoDTO;
import com.example.escola.controller.dto.responsavel.ResponsavelDTO;

import java.time.LocalDate;

public record AlunoResponseDTO(
        Long matricula,
        String nomeCompleto,
        String email,
        String telefoneContato,
        String cpf,
        String rg,
        LocalDate dataNascimento,
        EnderecoDTO endereco,
        ResponsavelDTO responsavel
) {
    public AlunoResponseDTO(Aluno aluno) {
        this(
                aluno.getMatricula(),
                aluno.getNomeCompleto(),
                aluno.getEmail(),
                aluno.getTelefoneContato(),
                aluno.getCpf(),
                aluno.getRg(),
                aluno.getDataNascimento(),
                // Delega a construção do EnderecoDTO para ele mesmo
                new EnderecoDTO(aluno.getEndereco()),
                // Lida com o responsável opcional de forma segura
                aluno.getResponsavel() != null ? new ResponsavelDTO(aluno.getResponsavel()) : null
        );
    }
}