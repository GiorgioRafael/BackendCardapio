package com.example.escola.dal.entities;
import jakarta.persistence.*;
import lombok.*;
import com.example.escola.dal.entities.Endereco;

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
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "responsavel_id") //nullabe
    private Responsavel responsavel;


}
