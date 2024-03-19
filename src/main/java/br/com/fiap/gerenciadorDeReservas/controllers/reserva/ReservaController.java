package br.com.fiap.gerenciadorDeReservas.controllers.reserva;

import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosConsultaReservasDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.CriarReservaUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.FecharReservaUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.ListarReservasPorRestauranteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe Controller das operações de Reserva
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    CriarReservaUseCase criarReservaUseCase;
    @Autowired
    FecharReservaUseCase fecharReservaUseCase;
    @Autowired
    ListarReservasPorRestauranteUseCase listarReservasPorRestauranteUseCase;

    @GetMapping("/listar-reservas/{restaurante_id}")
    public ResponseEntity<List<DadosConsultaReservasDTO>> consultarReservasPorRestaurante(@PathVariable Long restaurante_id) {

        return ResponseEntity.ok(listarReservasPorRestauranteUseCase.listarReservasPorRestaurante(restaurante_id));

    }


    @PostMapping("/criar-reserva")
    public ResponseEntity<DadosRetornoCriacaoReservaDTO> criarReserva(
            @RequestBody @Validated DadosCriacaoReservaDTO dadosCriacaoReservaDTO) throws IllegalAccessException {

        return ResponseEntity.ok(criarReservaUseCase.criarReserva(dadosCriacaoReservaDTO));

    }

    @PutMapping("/encerrar-reserva/{reserva_id}")
    public ResponseEntity<Void> encerrarReserva(@PathVariable Long reserva_id) {

        fecharReservaUseCase.fecharReserva(reserva_id);

        return ResponseEntity.noContent().build();

    }


}
