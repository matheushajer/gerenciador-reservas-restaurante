package br.com.fiap.gerenciadorDeReservas.controllers.restaurante;

import br.com.fiap.gerenciadorDeReservas.records.restaurante.*;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.*;
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
    @Autowired
    BuscarRestaurantePorCidadeUseCase buscarRestaurantePorCidadeUseCase;
    @Autowired
    BuscarRestaurantePorCulinariaUseCase buscarRestaurantePorCulinariaUseCase;
    @Autowired
    ReservaRestauranteUseCase reservaRestauranteUseCase;

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
            @RequestBody DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO) {

        return ResponseEntity.ok(criarRestauranteUseCase.criarRestaurante(dadosCriacaoRestauranteDTO));

    }

    @PostMapping("/criar-reserva")
    public ResponseEntity<?> criarReserva(@RequestBody DadosReservaRestauranteDTO dadosReservaRestauranteDTO) {
        return reservaRestauranteUseCase.reservaRestaurante(dadosReservaRestauranteDTO);
    }

    @PostMapping("/fechar-reserva")
    public ResponseEntity<?> fecharReserva(@RequestBody DadosFecharReservaRestauranteDTO dadosFecharReservaRestauranteDTO) {
        return reservaRestauranteUseCase.fecharReservaRestaurante(dadosFecharReservaRestauranteDTO);
    }

    @GetMapping("/listar-reserva")
    public ResponseEntity<?> listaReserva(@RequestBody DadosListaReservaRestauranteDTO listaReservaRestauranteDTO) {
        return reservaRestauranteUseCase.listaReservaRestaurante(listaReservaRestauranteDTO);
    }

}
