package com.example.escola.dal.entities;

import com.example.escola.dal.entities.Aluno;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@Data
@NoArgsConstructor
public class Endereco {

    @Id
    private String id;

    // Campos do endereço
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String cidade;
    private String estado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Pessoa pessoa;

    public Endereco(String cep, String logradouro, String numero, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;

    }
}