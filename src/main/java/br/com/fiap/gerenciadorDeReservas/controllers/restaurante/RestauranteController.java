package br.com.fiap.gerenciadorDeReservas.controllers.restaurante;

import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorNomeUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.CriarRestauranteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<DadosConsultaRestauranteDTO>> buscarRestaurantesPorNome(
            @RequestParam String nomeRestaurante) {

        List<DadosConsultaRestauranteDTO> dadosRestaurantes = buscarRestaurantePorNomeUseCase
                .buscarRestaurantes(nomeRestaurante);

        return ResponseEntity.ok(dadosRestaurantes);

    }


    @PostMapping("/criar-restaurante")
    public ResponseEntity<DadosCriacaoRestauranteDTO> criarRestaurante(
            @RequestBody DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO) {

        return ResponseEntity.ok(criarRestauranteUseCase.criarRestaurante(dadosCriacaoRestauranteDTO));

    }

}
