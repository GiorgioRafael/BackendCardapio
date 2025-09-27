package com.example.escola.controller;

import com.example.escola.controller.dto.professor.ProfessorRequestDTO;
import com.example.escola.controller.dto.professor.ProfessorResponseDTO;
import com.example.escola.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public List<ProfessorResponseDTO> getAllProfessores() {
        return service.getAllProfessors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> getProfessorById(@PathVariable String id) {
        try {
            ProfessorResponseDTO professor = service.getProfessorById(id);
            return ResponseEntity.ok(professor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> saveProfessor(@RequestBody ProfessorRequestDTO data) {
        ProfessorResponseDTO savedProfessor = service.createProfessor(data);
        return ResponseEntity.ok(savedProfessor);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> updateProfessor(@PathVariable String id, @RequestBody ProfessorRequestDTO data) {
        try {
            ProfessorResponseDTO updatedProfessor = service.updateProfessor(id, data);
            return ResponseEntity.ok(updatedProfessor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable String id) {
        try {
            service.deleteProfessor(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
