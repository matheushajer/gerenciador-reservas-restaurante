package br.com.fiap.gerenciadorDeReservas.controllers.cliente;

import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.cliente.CriarClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller das operações do Restaurante
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    CriarClienteUseCase criarClienteUseCase;

    @PostMapping("/criar-cliente")
    public ResponseEntity<DadosCriacaoClienteDTO> criarRestaurante(
            @RequestBody @Validated DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return ResponseEntity.ok(criarClienteUseCase.criarCliente(dadosCriacaoClienteDTO));

    }

}
