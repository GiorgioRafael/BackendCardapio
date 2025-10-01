package com.example.escola.application.repositories;

import com.example.escola.domain.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface Repository para a entidade Aluno.
 * Fornece todos os métodos de acesso a dados (CRUD) para Alunos.
 */
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
