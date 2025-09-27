package com.example.escola.dal.entities;

import com.example.escola.enums.ProfessorStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "professores")
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Pessoa {

    private LocalDate dataContratacao;
    private ProfessorStatus professorStatus;
    private String registroFuncional; //matricula de professor

    private String formacaoAcademica;
    private String biografia;

    @OneToMany(mappedBy = "professor")
    private List<Turma> turmas;

    @ManyToMany
    @JoinTable(
            name= "professores_disciplinas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;
}
