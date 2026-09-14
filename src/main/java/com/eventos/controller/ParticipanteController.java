package com.eventos.controller;

import com.eventos.dto.ParticipanteDTO;
import com.eventos.dto.ParticipanteResponseDTO;
import com.eventos.service.ParticipanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos/api/participante")
@RequiredArgsConstructor
public class ParticipanteController {

    private final ParticipanteService participanteService;

    @PostMapping
    public ResponseEntity<ParticipanteResponseDTO> cadastrar(@Valid @RequestBody ParticipanteDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(participanteService.cadastrar(dto));
    }
}
