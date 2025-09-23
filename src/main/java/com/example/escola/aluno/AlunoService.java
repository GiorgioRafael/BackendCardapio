package com.example.escola.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    
import java.security.SecureRandom;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public void matricularNovoAluno(AlunoRequestDTO dados) {
        Long matriculaUnica = gerarMatriculaUnica();

        Aluno novoAluno = new Aluno(dados);
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