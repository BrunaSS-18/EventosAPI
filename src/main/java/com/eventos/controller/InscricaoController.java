package com.eventos.controller;

import com.eventos.dto.InscricaoRequestDTO;
import com.eventos.dto.InscricaoResponseDTO;
import com.eventos.model.Inscricao;
import com.eventos.model.Participante;
import com.eventos.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    /**
     * RF05 - Inscrever um participante em um evento.
     */
    @PostMapping
    public ResponseEntity<InscricaoResponseDTO> inscrever(@RequestBody InscricaoRequestDTO request) {
        Inscricao inscricao = inscricaoService.inscreverParticipante(request.eventoId(), request.participanteId());

        InscricaoResponseDTO response = new InscricaoResponseDTO(
            inscricao.getId(),
            inscricao.getEvento().getNome(),
            inscricao.getParticipante().getNome(),
            inscricao.getDataInscricao()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * RF06 - Listar os participantes inscritos em um evento.
     */
    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<Participante>> listarPorEvento(@PathVariable Long eventoId) {
        List<Participante> participantes = inscricaoService.listarParticipantesPorEvento(eventoId);
        return ResponseEntity.ok(participantes);
    }

    /**
     * RF07 - Cancelar a inscrição de um participante em um evento.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        inscricaoService.cancelarInscricao(id);
        return ResponseEntity.noContent().build();
    }
}
