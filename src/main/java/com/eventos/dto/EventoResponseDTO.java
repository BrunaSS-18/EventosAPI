package com.eventos.dto;

import com.eventos.model.Participante;

import java.time.LocalDate;
import java.util.List;

public record EventoResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDate data,
    Integer capacidadeMaxima,
    List<String> participante
) {
}
