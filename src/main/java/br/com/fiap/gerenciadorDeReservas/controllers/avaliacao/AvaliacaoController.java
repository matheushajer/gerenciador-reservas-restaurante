package br.com.fiap.gerenciadorDeReservas.controllers.avaliacao;

import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.CriarAvaliacaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller das operações da avaliação.
 */
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    CriarAvaliacaoUseCase criarAvaliacaoUseCase;

    @PostMapping("/criar-avaliacao")
    public ResponseEntity<DadosRetornoCriacaoAvaliacaoDTO> criarAvaliacao(
            @RequestBody DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO) {

        return ResponseEntity.ok(criarAvaliacaoUseCase.criarAvaliacao(dadosCriacaoAvaliacaoDTO));

    }

}
