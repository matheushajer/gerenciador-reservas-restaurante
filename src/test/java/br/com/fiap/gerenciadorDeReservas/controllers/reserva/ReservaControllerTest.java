package br.com.fiap.gerenciadorDeReservas.controllers.reserva;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosConsultaReservasDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.CriarReservaUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.FecharReservaUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.ListarReservasPorRestauranteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ReservaControllerTest {
    @Mock
    CriarReservaUseCase criarReservaUseCase;
    @Mock
    FecharReservaUseCase fecharReservaUseCase;
    @Mock
    ListarReservasPorRestauranteUseCase listarReservasPorRestauranteUseCase;
    @InjectMocks
    ReservaController reservaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarReservasPorRestaurante() {
        when(listarReservasPorRestauranteUseCase.listarReservasPorRestaurante(anyLong()))
                .thenReturn(
                        List.of(new DadosConsultaReservasDTO(LocalDateTime.of(2024, Month.MARCH, 23, 8, 21, 36), StatusReservaEnum.ABERTA))
                );

        ResponseEntity<List<DadosConsultaReservasDTO>> result =
                reservaController.consultarReservasPorRestaurante(1L);

        assertEquals(
                new ResponseEntity<List<DadosConsultaReservasDTO>>(
                        List.of(new DadosConsultaReservasDTO(LocalDateTime.of(2024, Month.MARCH, 23, 8, 21, 36), StatusReservaEnum.ABERTA)),
                        null,
                        200
                ),
                result
        );
    }

    @Test
    void testCriarReserva() throws IllegalAccessException {
        when(criarReservaUseCase.criarReserva(any()))
                .thenReturn(
                        new DadosRetornoCriacaoReservaDTO(
                                "nomeRestauramte",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                LocalDateTime.of(2024, Month.MARCH, 23, 8, 21, 36)
                        )
                );

        ResponseEntity<DadosRetornoCriacaoReservaDTO> result =
                reservaController.criarReserva(
                        new DadosCriacaoReservaDTO(
                                LocalDateTime.of(2024, Month.MARCH, 23, 8, 21, 36), 1L, 1L
                        )
                );

        assertEquals(
                new ResponseEntity<DadosRetornoCriacaoReservaDTO>(
                        new DadosRetornoCriacaoReservaDTO(
                                "nomeRestauramte",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                LocalDateTime.of(2024, Month.MARCH, 23, 8, 21, 36)
                        ),
                        null,
                        200
                ),
                result
        );
    }

    @Test
    void testEncerrarReserva() {
        ResponseEntity<Void> result = reservaController.encerrarReserva(1L);
        verify(fecharReservaUseCase).fecharReserva(anyLong());
        assertEquals(new ResponseEntity<Void>(null, null, 204), result);
    }

}
