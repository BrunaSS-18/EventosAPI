package com.eventos.dto;

import java.time.LocalDate;

public record InscricaoResponseDTO(
    Long id,
    String eventoNome,
    String participanteNome,
    LocalDate dataInscricao
) {
}
