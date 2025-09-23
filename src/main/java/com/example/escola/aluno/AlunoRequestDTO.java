package com.example.escola.aluno;
import java.time.LocalDate;
import com.example.escola.aluno.EnderecoDTO;   // <-- Import adicionado
import com.example.escola.aluno.ResponsavelDTO; // <-- Import adicionado

//DATA TRANSFER OBJECT DO ALUNO, aninhado em partes (colocado no mesmo arquivo por conveniencia)
public record AlunoRequestDTO(
        String nomeCompleto,
        String email,
        String telefoneContato,
        String cpf,
        String rg,
        LocalDate dataNascimento,

        // DTOs aninhados
        EnderecoDTO endereco,
        ResponsavelDTO responsavel
) {}
