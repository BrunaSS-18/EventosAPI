package com.eventos.controller;

import com.eventos.dto.EventoDTO;
import com.eventos.dto.EventoResponseDTO;
import com.eventos.model.Evento;
import com.eventos.service.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
@Tag(name = "Evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<EventoResponseDTO> listarTodos() {
        return eventoService.listar();
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> cadastrar(@Valid @RequestBody EventoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.cadastrar(dto));
    }


    @GetMapping("/{id}")
    public EventoResponseDTO buscarPorId(@PathVariable Long id) {
        return eventoService.buscarPorId(id);
    }
}
