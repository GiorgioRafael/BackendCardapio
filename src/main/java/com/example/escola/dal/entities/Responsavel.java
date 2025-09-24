package com.example.escola.dal.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "responsaveis")
@Data
public class Responsavel {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
}
