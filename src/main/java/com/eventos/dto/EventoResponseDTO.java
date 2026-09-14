package com.eventos.dto;

import java.time.LocalDate;

public record EventoResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDate data,
    Integer capacidadeMaxima
) {
}
