package com.riobranco.parkingflow.pesquisa.controller;

import com.riobranco.parkingflow.pesquisa.model.Avaliacao;
import com.riobranco.parkingflow.pesquisa.model.ResultadoPesquisa;
import com.riobranco.parkingflow.pesquisa.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Avaliacao avaliacao) {
        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            return ResponseEntity.badRequest().body("Nota invalida. Use um valor de 1 a 5.");
        }

        if (avaliacao.getUsuario() == null || avaliacao.getUsuario().isBlank()) {
            return ResponseEntity.badRequest().body("Usuario e obrigatorio.");
        }

        Avaliacao salva = avaliacaoService.registrar(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping("/resultado")
    public ResponseEntity<ResultadoPesquisa> resultado() {
        ResultadoPesquisa resultado = avaliacaoService.calcularResultado();
        return ResponseEntity.ok(resultado);
    }
}