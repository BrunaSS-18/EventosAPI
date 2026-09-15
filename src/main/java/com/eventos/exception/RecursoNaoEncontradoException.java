package com.eventos.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException() {
        super("Lanche não encontrado com ID selecionado");
    }
}
