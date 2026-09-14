package com.eventos.service;

import com.eventos.model.Evento;
import com.eventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listar(){
        return eventoRepository.findAll();
    }

    public Evento salvar(Evento evento){
        return eventoRepository.save(evento);
    }

    public Evento buscarPorId(Long id){
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
    }
}
