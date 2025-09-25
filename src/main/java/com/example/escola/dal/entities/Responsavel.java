package com.example.escola.dal.entities;

import jakarta.persistence.*; // Importe as anotações da JPA
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // <<-- Define que esta classe é uma entidade do banco de dados
@Table(name = "responsaveis") // <<-- Define o nome da tabela
@Data // <<-- Combina @Getter, @Setter, @ToString, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Responsavel {

    @Id // <<-- Define que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <<-- Define que o ID será gerado automaticamente
    private Long id;

    private String nome;
    private String cpf;
    private String telefoneContato;
}