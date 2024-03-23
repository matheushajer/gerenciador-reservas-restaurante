package br.com.fiap.gerenciadorDeReservas.controllers.avaliacao;

import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.avaliacao.BuscarAvaliacoesPorRestauranteUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.avaliacao.CriarAvaliacaoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller das operações da avaliação.
 */
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    CriarAvaliacaoUseCase criarAvaliacaoUseCase;
    @Autowired
    BuscarAvaliacoesPorRestauranteUseCase buscarAvaliacoesPorRestauranteUseCase;

    @GetMapping("/listar-avaliacoes/{restaurante_id}")
    public Page<DadosRetornoCriacaoAvaliacaoDTO> listarAvaliacoesPorRestaurantePaginado(
            @PathVariable Long restaurante_id,
            Pageable pageable) {
        return buscarAvaliacoesPorRestauranteUseCase.listarAvaliacoesPorRestaurante(restaurante_id, pageable);
    }

    @PostMapping("/criar-avaliacao")
    public ResponseEntity<DadosRetornoCriacaoAvaliacaoDTO> criarAvaliacao(
            @RequestBody DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO) {

        return ResponseEntity.ok(criarAvaliacaoUseCase.criarAvaliacao(dadosCriacaoAvaliacaoDTO));

    }

}
