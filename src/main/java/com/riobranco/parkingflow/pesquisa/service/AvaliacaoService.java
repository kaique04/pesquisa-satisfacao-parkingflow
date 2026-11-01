package com.riobranco.parkingflow.pesquisa.service;

import com.riobranco.parkingflow.pesquisa.model.Avaliacao;
import com.riobranco.parkingflow.pesquisa.model.ResultadoPesquisa;
import com.riobranco.parkingflow.pesquisa.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao registrar(Avaliacao avaliacao) {
        avaliacao.setData(LocalDateTime.now());
        return avaliacaoRepository.save(avaliacao);
    }

    public ResultadoPesquisa calcularResultado() {
        List<Avaliacao> todas = avaliacaoRepository.findAll();

        ResultadoPesquisa resultado = new ResultadoPesquisa();

        if (todas.isEmpty()) {
            resultado.setTemDados(false);
            return resultado;
        }

        resultado.setTemDados(true);
        resultado.setTotalAvaliacoes(todas.size());

        double somaNotas = 0;
        long quantidadePositivas = 0;

        for (Avaliacao avaliacao : todas) {
            somaNotas += avaliacao.getNota();
            if (avaliacao.getNota() >= 4) {
                quantidadePositivas++;
            }
        }

        double mediaGeral = somaNotas / todas.size();
        double percentualPositivas = (quantidadePositivas * 100.0) / todas.size();
        double percentualNegativas = 100.0 - percentualPositivas;

        resultado.setMediaGeral(arredondar(mediaGeral));
        resultado.setPercentualPositivas(arredondar(percentualPositivas));
        resultado.setPercentualNegativas(arredondar(percentualNegativas));
        resultado.setAlerta(mediaGeral < 3);
        resultado.setTendencia(calcularTendencia());

        return resultado;
    }

    private String calcularTendencia() {
        LocalDateTime inicioSemanaAtual = LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay();
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioSemanaAnterior = inicioSemanaAtual.minusWeeks(1);

        List<Avaliacao> semanaAtual = avaliacaoRepository.findByDataBetween(inicioSemanaAtual, agora);
        List<Avaliacao> semanaAnterior = avaliacaoRepository.findByDataBetween(inicioSemanaAnterior, inicioSemanaAtual);

        if (semanaAnterior.isEmpty() || semanaAtual.isEmpty()) {
            return "sem dados suficientes para comparar";
        }

        double mediaAtual = semanaAtual.stream().mapToInt(Avaliacao::getNota).average().orElse(0);
        double mediaAnterior = semanaAnterior.stream().mapToInt(Avaliacao::getNota).average().orElse(0);

        if (mediaAtual > mediaAnterior) {
            return "subindo";
        } else if (mediaAtual < mediaAnterior) {
            return "descendo";
        } else {
            return "estavel";
        }
    }

    private double arredondar(double valor) {
        return Math.round(valor * 10.0) / 10.0;
    }
}