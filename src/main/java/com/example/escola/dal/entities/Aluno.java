package com.example.escola.dal.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Table(name= "alunos") //foods
@Entity(name ="alunos") //foods
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class Aluno {
    @Id
    // --- Aluno
    private Long matricula; // primary key
    private String nomeCompleto; //definir tamanho max
    private String email; // fica para o reponsável
    private String telefoneContato;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    // --- Endereço
    private String enderecoCep;
    private String enderecoLogradouro;
    private String enderecoNumero;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoEstado;
    // --- Responsável
    private String responsavelNome;
    private String responsavelCpf;
    private String responsavelTelefoneContato;


}
