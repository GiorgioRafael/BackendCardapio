package com.example.escola.aluno;
import com.example.escola.aluno.AlunoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Table(name= "alunos") //foods
@Entity(name ="alunos") //foods
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Aluno {
    @Id
    // --- Aluno
    private Long matricula;
    private String nomeCompleto;
    private String email;
    private String telefoneContato;
    private String cpf;
    private String rg;
    private LocalDate dataNascimento;
    //
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



    public Aluno(AlunoRequestDTO data){
        this.nomeCompleto = data.nomeCompleto();
        this.email = data.email();
        this.telefoneContato = data.telefoneContato();
        this.cpf = data.cpf();
        this.rg = data.rg();
        this.dataNascimento = data.dataNascimento();

        //acessando dto com metodo (com parenteses)
        this.enderecoCep = data.endereco().cep(); // < parenteses em cep
        this.enderecoLogradouro = data.endereco().logradouro();
        this.enderecoNumero = data.endereco().numero();
        this.enderecoBairro = data.endereco().bairro();
        this.enderecoCidade = data.endereco().cidade();
        this.enderecoEstado = data.endereco().estado();

        //acessando dto do responsavel
        this.responsavelNome = data.responsavel().nome();
        this.responsavelCpf = data.responsavel().cpf();
        this.responsavelTelefoneContato = data.responsavel().telefoneContato();


    }
}
