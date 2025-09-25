package com.example.escola.controller;

import com.example.escola.controller.dto.aluno.AlunoRequestDTO;
import com.example.escola.controller.dto.aluno.AlunoResponseDTO;
import com.example.escola.dal.entities.Aluno;
import com.example.escola.dal.repositories.AlunoRepository;
import com.example.escola.service.AlunoService; // Importe o service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("alunos")
public class AlunoController {

    // 1. INJETE O SERVICE EM VEZ DO REPOSITORY
    @Autowired
    private AlunoService service;

    // Injeção do repository ainda é útil para operações simples como GET e DELETE
    @Autowired
    private AlunoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders="*")
    @PostMapping
    public ResponseEntity<Void> saveAluno(@RequestBody AlunoRequestDTO data) {
        // 2. CHAME O SERVICE PARA EXECUTAR A LÓGICA DE NEGÓCIO
        service.matricularNovoAluno(data);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<AlunoResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(AlunoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{matricula}") // Alterado para {matricula} para consistência
    public void deleteAluno(@PathVariable Long matricula) {
        repository.deleteById(matricula);
    }

    @PutMapping("/{matricula}") // Alterado para {matricula}
    public void updateAluno(@PathVariable Long matricula, @RequestBody AlunoRequestDTO data){
        // Idealmente, a lógica de update também estaria em um método no AlunoService
        Aluno alunoData = repository.getReferenceById(matricula);
        // ... (lógica de update)
        repository.save(alunoData);
    }
}
