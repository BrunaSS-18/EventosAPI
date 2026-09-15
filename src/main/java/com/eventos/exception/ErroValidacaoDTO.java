package com.eventos.exception;

import java.util.List;

public record ErroValidacaoDTO(int status, List<ErroCampoDTO> erros) {
}