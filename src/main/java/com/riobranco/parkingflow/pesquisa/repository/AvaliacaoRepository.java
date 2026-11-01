package com.riobranco.parkingflow.pesquisa.repository;

import com.riobranco.parkingflow.pesquisa.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
}