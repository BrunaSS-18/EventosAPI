package com.eventos.service;

import com.eventos.dto.EventoDTO;
import com.eventos.dto.EventoResponseDTO;
import com.eventos.model.Evento;
import com.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public EventoResponseDTO cadastrar(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setNome(dto.nome());
        evento.setDescricao(dto.descricao());
        evento.setData(dto.data());
        evento.setCapacidadeMaxima(dto.capacidadeMaxima());

        return toResponseDTO(eventoRepository.save(evento));
    }


    public List<EventoResponseDTO> listar(){
        return eventoRepository.findAll().stream().map(this::toResponseDTO)
                .toList();
    }

    public EventoResponseDTO buscarPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("teste"));
        return toResponseDTO(evento);
    }

    private EventoResponseDTO toResponseDTO(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getData(),
                evento.getCapacidadeMaxima()
        );
    }

}
