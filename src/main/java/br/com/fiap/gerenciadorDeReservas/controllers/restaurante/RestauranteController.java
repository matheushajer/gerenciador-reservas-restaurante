package br.com.fiap.gerenciadorDeReservas.controllers.restaurante;

import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorCidadeUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorCulinariaUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorNomeUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.CriarRestauranteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe Controller das operações do Restaurante
 */
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    CriarRestauranteUseCase criarRestauranteUseCase;
    @Autowired
    BuscarRestaurantePorNomeUseCase buscarRestaurantePorNomeUseCase;
    @Autowired
    BuscarRestaurantePorCidadeUseCase buscarRestaurantePorCidadeUseCase;
    @Autowired
    BuscarRestaurantePorCulinariaUseCase buscarRestaurantePorCulinariaUseCase;

    @GetMapping
    public ResponseEntity<List<DadosConsultaRestauranteDTO>> buscarRestaurantesPorNome(
            @RequestParam String nomeRestaurante) {

        List<DadosConsultaRestauranteDTO> dadosRestaurantes = buscarRestaurantePorNomeUseCase
                .buscarRestaurantesPorNome(nomeRestaurante);


        return ResponseEntity.ok(dadosRestaurantes);

    }

    @GetMapping("/cidade")
    public ResponseEntity<List<DadosConsultaRestauranteDTO>> buscarRestaurantesPorCidade(
            @RequestParam String cidadeRestaurante) {

        List<DadosConsultaRestauranteDTO> dadosRestaurantes = buscarRestaurantePorCidadeUseCase
                .buscarRestaurantesPorCidade(cidadeRestaurante);


        return ResponseEntity.ok(dadosRestaurantes);

    }

    @GetMapping("/culinaria")
    public ResponseEntity<List<DadosConsultaRestauranteDTO>> buscarRestaurantesPorCulinaria(
            @RequestParam String culinariaRestaurante) {

        List<DadosConsultaRestauranteDTO> dadosRestaurantes = buscarRestaurantePorCulinariaUseCase
                .buscarRestaurantesPorCulinaria(culinariaRestaurante);


        return ResponseEntity.ok(dadosRestaurantes);

    }

    @PostMapping("/criar-restaurante")
    public ResponseEntity<DadosCriacaoRestauranteDTO> criarRestaurante(
            @RequestBody @Validated DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO) {

        return ResponseEntity.ok(criarRestauranteUseCase.criarRestaurante(dadosCriacaoRestauranteDTO));

    }

}
