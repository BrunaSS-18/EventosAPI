package com.eventos.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoDTO (
    String nome,
    String descricao,
    LocalDate data,

    @NotNull(message = "O nome é obrigatório")
    Integer capacidadeMaxima
    ){
}
