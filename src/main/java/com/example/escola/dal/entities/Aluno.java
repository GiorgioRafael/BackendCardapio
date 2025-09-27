package com.example.escola.dal.entities;

import com.example.escola.controller.dto.aluno.AlunoRequestDTO;
import com.example.escola.dal.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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

    @ManyToMany(mappedBy = "alunos")
    private List<Turma> turmas;


    public Aluno(AlunoRequestDTO data) {
        // Mapeia os dados simples
        this.setNomeCompleto(data.nomeCompleto());
        this.setEmail(data.email());
        this.setCpf(data.cpf());
        this.setRg(data.rg());
        this.setDataNascimento(data.dataNascimento());
        this.setTelefoneContato(data.telefoneContato());

        // Cria e associa a nova entidade Endereco a partir do EnderecoDTO
        if (data.endereco() != null) {
            this.setEndereco(new Endereco(data.endereco()));
        }

        // Associa o Responsavel (a lógica de buscar ou criar o responsável ficaria no Service)
        // Aqui, estamos apenas preparando o Aluno. O Responsavel precisa ser gerenciado separadamente.
        // this.responsavel = ... ; // Isso será feito no Service
    }
}