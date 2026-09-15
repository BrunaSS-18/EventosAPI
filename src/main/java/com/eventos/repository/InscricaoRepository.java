package com.eventos.repository;

import com.eventos.model.Inscricao;
import com.eventos.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    boolean existsByEventoIdAndParticipanteId(Long eventoId, Long participanteId);

    long countByEventoId(Long eventoId);

    @Query("SELECT i.participante FROM Inscricao i WHERE i.evento.id = :eventoId")
    List<Participante> findParticipantesByEventoId(@Param("eventoId") Long eventoId);
}