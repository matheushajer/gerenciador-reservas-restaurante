package br.com.fiap.gerenciadorDeReservas.controllers.cliente;

import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return ResponseEntity.ok(criarClienteUseCase.criarCliente(dadosCriacaoClienteDTO));

    }

}
