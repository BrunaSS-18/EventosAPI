package com.eventos.dto;

import java.time.LocalDate;

public record InscricaoRequestDTO(Long eventoId, Long participanteId, LocalDate dataInscricao) {
}
