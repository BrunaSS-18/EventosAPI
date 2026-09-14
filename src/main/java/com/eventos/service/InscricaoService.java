package com.eventos.service;

import com.eventos.model.Evento;
import com.eventos.model.Inscricao;
import com.eventos.model.Participante;
import com.eventos.repository.EventoRepository;
import com.eventos.repository.InscricaoRepository;
import com.eventos.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Transactional
    public Inscricao inscreverParticipante(Long eventoId, Long participanteId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));

        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participante não encontrado"));

        // RN02 — Inscrição duplicada
        boolean jaInscrito = inscricaoRepository.findAll().stream()
                .anyMatch(i -> i.getEvento().getId().equals(eventoId) && i.getParticipante().getId().equals(participanteId));
        if (jaInscrito) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O participante já está inscrito neste evento");
        }

        // RN01 — Limite de vagas
        long totalInscritos = inscricaoRepository.findAll().stream()
                .filter(i -> i.getEvento().getId().equals(eventoId))
                .count();

        if (evento.getCapacidadeMaxima() != null && totalInscritos >= evento.getCapacidadeMaxima()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O evento está lotado");
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);
        inscricao.setDataInscricao(LocalDate.now());

        return inscricaoRepository.save(inscricao);
    }

    public List<Participante> listarParticipantesPorEvento(Long eventoId) {
        return inscricaoRepository.findAll().stream()
                .filter(i -> i.getEvento().getId().equals(eventoId))
                .map(Inscricao::getParticipante)
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelarInscricao(Long inscricaoId) {
        if (!inscricaoRepository.existsById(inscricaoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscrição não encontrada");
        }
        inscricaoRepository.deleteById(inscricaoId);
        // RN04 — Cancelamento libera vaga é implícito ao remover o registro
    }
}
