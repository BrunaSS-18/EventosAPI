package com.eventos.controller;

import com.eventos.model.Evento;
import com.eventos.service.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
@Tag(name = "Evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> listar(){
        return eventoService.listar();
    }

    @PostMapping
    public Evento salvar(@RequestBody Evento evento){
        return eventoService.salvar(evento);
    }

    @GetMapping("/{id}")
    public Evento buscarPorId(@PathVariable Long id){
        return eventoService.buscarPorId(id);
    }
}
