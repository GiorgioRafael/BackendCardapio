package com.example.escola.service;

import com.example.escola.dal.entities.Aluno;
import com.example.escola.dal.entities.Endereco;
import com.example.escola.dal.entities.Responsavel;
import com.example.escola.dal.repositories.AlunoRepository;
import com.example.escola.dal.repositories.ResponsavelRepository;
import com.example.escola.controller.dto.aluno.AlunoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    private Aluno convertToEntity(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto(dto.nomeCompleto());
        aluno.setEmail(dto.email());
        aluno.setTelefoneContato(dto.telefoneContato());
        aluno.setCpf(dto.cpf());
        aluno.setRg(dto.rg());
        aluno.setDataNascimento(dto.dataNascimento());

        // Cria e associa o endereço
        if (dto.endereco() != null) {
            Endereco endereco = new Endereco(
                dto.endereco().cep(),
                dto.endereco().logradouro(),
                dto.endereco().numero(),
                dto.endereco().bairro(),
                dto.endereco().cidade(),
                dto.endereco().estado()
            );
            endereco.setPessoa(aluno);
            aluno.setEndereco(endereco);
        }

        // Busca ou cria o responsável
        if (dto.responsavel() != null) {Responsavel responsavel = dto.responsavel().toEntity();
            responsavel = responsavelRepository.save(responsavel); // Salva o responsável antes de associar
            aluno.setResponsavel(responsavel);
        }
        return aluno;
    }

    public void matricularNovoAluno(AlunoRequestDTO dados) {
        Long matriculaUnica = gerarMatriculaUnica();

        // Usar o método de conversão em vez do construtor
        Aluno novoAluno = convertToEntity(dados);
        novoAluno.setMatricula(matriculaUnica);

        repository.save(novoAluno);
    }

    private Long gerarMatriculaUnica() {
        Long matricula;
        do {
            long numeroAleatorio = 100000 + new SecureRandom().nextInt(900000);
            matricula = numeroAleatorio;
        } while (repository.existsById(matricula));

        return matricula;
    }
}
