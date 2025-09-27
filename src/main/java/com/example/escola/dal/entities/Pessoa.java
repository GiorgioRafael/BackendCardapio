package com.example.escola.dal.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Estratégia de herança
@Data
//abstract por que não existira pessoa generica
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nomeCompleto;
    private String email; //null
    private String cpf; //null
    private String rg; //null
    private LocalDate dataNascimento;
    private String telefoneContato;


    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Endereco endereco;

    @OneToOne(mappedBy = "pessoa")
    private User user;
}