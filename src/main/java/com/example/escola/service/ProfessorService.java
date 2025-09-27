package com.example.escola.service;

import com.example.escola.controller.dto.professor.ProfessorRequestDTO;
import com.example.escola.controller.dto.professor.ProfessorResponseDTO;
import com.example.escola.dal.entities.Endereco;
import com.example.escola.dal.entities.Professor;
import com.example.escola.dal.repositories.ProfessorRepository;
import com.example.escola.enums.ProfessorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorResponseDTO createProfessor(ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNomeCompleto(dto.nomeCompleto());
        professor.setEmail(dto.email());
        professor.setCpf(dto.cpf());
        professor.setTelefoneContato(dto.telefoneContato());
        professor.setDataContratacao(dto.dataContratacao());

        // Gerar registro funcional no formato PROF-AACCC (AA=ano atual, CCC=contador)
        String registroFuncional = gerarRegistroFuncional();
        professor.setRegistroFuncional(registroFuncional);

        // Definir status como ATIVO por padrão
        professor.setProfessorStatus(ProfessorStatus.ATIVO);

        // Endereco
        if (dto.endereco() != null) {
            Endereco endereco = new Endereco(dto.endereco());
            endereco.setPessoa(professor);
            professor.setEndereco(endereco);
        }

        Professor saved = professorRepository.save(professor);
        return new ProfessorResponseDTO(saved);
    }

    public List<ProfessorResponseDTO> getAllProfessors() {
        return professorRepository.findAll().stream()
                .map(ProfessorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ProfessorResponseDTO getProfessorById(String id) {
        Optional<Professor> optional = professorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Professor not found");
        }
        return new ProfessorResponseDTO(optional.get());
    }

    private String gerarRegistroFuncional() {
        // Obtém o ano atual (últimos 2 dígitos)
        int anoAtual = LocalDate.now().getYear() % 100;

        // Prefixo do registro
        String prefixo = "PROF-" + anoAtual;

        // Busca todos os professores e filtra os que têm registro começando com o prefixo
        List<Professor> professoresAnoAtual = professorRepository.findAll().stream()
                .filter(p -> p.getRegistroFuncional() != null &&
                        p.getRegistroFuncional().startsWith(prefixo))
                .collect(Collectors.toList());

        // Determina o próximo número sequencial
        int contador = 1;
        if (!professoresAnoAtual.isEmpty()) {
            // Extrai o maior contador atual
            contador = professoresAnoAtual.stream()
                    .map(p -> {
                        try {
                            return Integer.parseInt(p.getRegistroFuncional().substring(prefixo.length()));
                        } catch (NumberFormatException e) {
                            return 0;
                        }
                    })
                    .max(Integer::compare)
                    .orElse(0) + 1;
        }

        // Formata o contador com zeros à esquerda para ter 3 dígitos
        String contadorStr = String.format("%03d", contador);

        return prefixo + contadorStr;
    }

    public ProfessorResponseDTO updateProfessor(String id, ProfessorRequestDTO dto) {
        Optional<Professor> optional = professorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Professor not found");
        }
        Professor professor = optional.get();

        // Atualiza somente os campos não nulos
        if (dto.nomeCompleto() != null) {
            professor.setNomeCompleto(dto.nomeCompleto());
        }
        if (dto.email() != null) {
            professor.setEmail(dto.email());
        }
        if (dto.cpf() != null) {
            professor.setCpf(dto.cpf());
        }
        if (dto.telefoneContato() != null) {
            professor.setTelefoneContato(dto.telefoneContato());
        }
        if (dto.dataContratacao() != null) {
            professor.setDataContratacao(dto.dataContratacao());
        }

        // Atualiza o endereço somente se estiver presente no DTO
        if (dto.endereco() != null) {
            Endereco endereco = professor.getEndereco();
            if (endereco == null) {
                endereco = new Endereco(dto.endereco());
                endereco.setPessoa(professor);
                professor.setEndereco(endereco);
            } else {
                // Atualiza somente os campos não nulos do endereço
                if (dto.endereco().cep() != null) {
                    endereco.setCep(dto.endereco().cep());
                }
                if (dto.endereco().logradouro() != null) {
                    endereco.setLogradouro(dto.endereco().logradouro());
                }
                if (dto.endereco().numero() != null) {
                    endereco.setNumero(dto.endereco().numero());
                }
                if (dto.endereco().bairro() != null) {
                    endereco.setBairro(dto.endereco().bairro());
                }
                if (dto.endereco().cidade() != null) {
                    endereco.setCidade(dto.endereco().cidade());
                }
                if (dto.endereco().estado() != null) {
                    endereco.setEstado(dto.endereco().estado());
                }
            }
        }

        Professor saved = professorRepository.save(professor);
        return new ProfessorResponseDTO(saved);
    }

    public void deleteProfessor(String id) {
        professorRepository.deleteById(id);
    }
}
