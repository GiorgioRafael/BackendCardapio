package com.example.escola.services;

import com.example.escola.dal.entities.Aluno;
import com.example.escola.dal.repositories.AlunoRepository;
import com.example.escola.dto.aluno.AlunoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    private Aluno convertToEntity(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto(dto.nomeCompleto());
        aluno.setEmail(dto.email());
        aluno.setTelefoneContato(dto.telefoneContato());
        aluno.setCpf(dto.cpf());
        aluno.setRg(dto.rg());
        aluno.setDataNascimento(dto.dataNascimento());
        // Endereço
        if (dto.endereco() != null) {
            aluno.setEnderecoCep(dto.endereco().cep());
            aluno.setEnderecoLogradouro(dto.endereco().logradouro());
            aluno.setEnderecoNumero(dto.endereco().numero());
            aluno.setEnderecoBairro(dto.endereco().bairro());
            aluno.setEnderecoCidade(dto.endereco().cidade());
            aluno.setEnderecoEstado(dto.endereco().estado());
        }
        // Responsável
        if (dto.responsavel() != null) {
            aluno.setResponsavelNome(dto.responsavel().nome());
            aluno.setResponsavelCpf(dto.responsavel().cpf());
            aluno.setResponsavelTelefoneContato(dto.responsavel().telefoneContato());
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
