package com.example.escola.dal.entities;

import com.example.escola.dal.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "alunos")
@Data
@EqualsAndHashCode(callSuper = true) // Importante para herança com Lombok
public class Aluno extends Pessoa {

    // O 'id', 'nomeCompleto', 'email', 'cpf' e 'endereco' já são herdados de Pessoa.

    @Column(unique = true, nullable = false)
    private Long matricula; // Este é o identificador de negócio, não a Primary Key.

    // --- Relacionamentos Específicos do Aluno
    @ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = true) // Pode ser nulo
    private Responsavel responsavel;

}