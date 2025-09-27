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
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    private Aluno convertToEntity(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno(dto);
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
    // --- NOVO MÉTODO PARA MATRICULAR VÁRIOS ALUNOS ---
    public void matricularNovosAlunos(List<AlunoRequestDTO> dtoList) {
        List<Aluno> novosAlunos = new ArrayList<>();

        // 1. Percorre a lista de DTOs recebida
        for (AlunoRequestDTO dados : dtoList) {
            Long matriculaUnica = gerarMatriculaUnica();
            Aluno novoAluno = new Aluno(dados);
            novoAluno.setMatricula(matriculaUnica);
            novosAlunos.add(novoAluno); // Adiciona o novo aluno preparado à lista
        }

        // 2. Salva TODOS os novos alunos no banco de uma só vez
        repository.saveAll(novosAlunos);
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
