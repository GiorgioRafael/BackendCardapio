    package com.example.escola.controller.dto.responsavel; // Sugestão de novo pacote

    import com.example.escola.dal.entities.Responsavel;

    public record ResponsavelDTO(
            String nome,
            String cpf,
            String telefoneContato
    ) {
        // Novo construtor que aceita a entidade Responsavel
        public ResponsavelDTO(Responsavel responsavel) {
            this(
                    responsavel.getNome(),
                    responsavel.getCpf(),
                    responsavel.getTelefoneContato()
            );
        }
    }