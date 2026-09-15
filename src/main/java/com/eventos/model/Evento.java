package com.eventos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private LocalDate data;
    
    @Column(nullable = true)
    private String local;

    @Column(nullable = true)
    private Integer capacidadeMaxima;

    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    private List<Inscricao> inscricaos = new ArrayList<>();
}

