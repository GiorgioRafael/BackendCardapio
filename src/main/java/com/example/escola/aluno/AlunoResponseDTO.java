package com.example.escola.aluno;
import java.time.LocalDate;
import com.example.escola.aluno.EnderecoDTO;   // <-- Import adicionado
import com.example.escola.aluno.ResponsavelDTO; // <-- Import adicionado
import com.example.escola.aluno.Aluno;     // <-- ÚNICA LINHA FALTANTE
/**
 * DTO para ENVIAR os dados de um Aluno como resposta da API.
 * Contém os campos que devem ser visíveis para o cliente, incluindo a matrícula.
 */
public record AlunoResponseDTO(
        Long matricula,
        String nomeCompleto,
        String email,
        String telefoneContato,
        String cpf,
        String rg,
        LocalDate dataNascimento,

        // DTOs aninhados
        EnderecoDTO endereco,
        ResponsavelDTO responsavel
) {
    /**
     * Construtor que transforma uma Entidade Aluno (vinda do banco)
     * em um AlunoResponseDTO (que será enviado como JSON).
     */
    public AlunoResponseDTO(Aluno aluno) {
        this(
                aluno.getMatricula(),
                aluno.getNomeCompleto(),
                aluno.getEmail(),
                aluno.getTelefoneContato(),
                aluno.getCpf(),
                aluno.getRg(),
                aluno.getDataNascimento(),
                new EnderecoDTO(
                        aluno.getEnderecoCep(),
                        aluno.getEnderecoLogradouro(),
                        aluno.getEnderecoNumero(),
                        aluno.getEnderecoBairro(),
                        aluno.getEnderecoCidade(),
                        aluno.getEnderecoEstado()
                ),
                new ResponsavelDTO(
                        aluno.getResponsavelNome(),
                        aluno.getResponsavelCpf(),
                        aluno.getResponsavelTelefoneContato()
                )
        );
    }
}
