package br.com.fiap.gerenciadorDeReservas.controllers.reserva;

import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.CriarReservaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller das operações de Reserva
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    CriarReservaUseCase criarReservaUseCase;

    @PostMapping("/criar-reserva")
    public ResponseEntity<DadosRetornoCriacaoReservaDTO> criarRestaurante(
            @RequestBody @Validated DadosCriacaoReservaDTO dadosCriacaoReservaDTO) throws IllegalAccessException {

        return ResponseEntity.ok(criarReservaUseCase.criarReserva(dadosCriacaoReservaDTO));

    }


}
