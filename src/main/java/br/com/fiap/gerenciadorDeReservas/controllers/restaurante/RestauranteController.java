package br.com.fiap.gerenciadorDeReservas.controllers.restaurante;

import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.CriarRestauranteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller das operações do Restaurante
 */
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    CriarRestauranteUseCase criarRestauranteUseCase;

    @PostMapping("/criar-restaurante")
    public ResponseEntity<DadosCriacaoRestauranteDTO> criarLogin(
            @RequestBody DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO) {

        return ResponseEntity.ok(criarRestauranteUseCase.criarRestaurante(dadosCriacaoRestauranteDTO));

    }

}
