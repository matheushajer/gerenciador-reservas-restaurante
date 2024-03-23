package br.com.fiap.gerenciadorDeReservas.usecases.reserva;

import br.com.fiap.gerenciadorDeReservas.adapters.reserva.ReservaAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosConsultaReservasDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class ListarReservasPorRestauranteUseCaseTest {
    @Mock
    ReservaRepository reservaRepository;
    @Mock
    RestauranteRepository restauranteRepository;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ReservaAdapter reservaAdapter;
    @InjectMocks
    ListarReservasPorRestauranteUseCase listarReservasPorRestauranteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarReservasPorRestaurante() {
        when(reservaRepository.findByRestauranteEntityId(anyLong()))
                .thenReturn(List.of(new ReservaEntity(StatusReservaEnum.ABERTA,
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 39, 27),
                        null, null)));
        when(reservaAdapter.converterParaDadosConsultaPorRestaurante(any()))
                .thenReturn(List.of(new DadosConsultaReservasDTO(
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 39, 27),
                        StatusReservaEnum.ABERTA
                )));

        List<DadosConsultaReservasDTO> result =
                listarReservasPorRestauranteUseCase.listarReservasPorRestaurante(1L);

        Assertions.assertEquals(
                List.of(new DadosConsultaReservasDTO(
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 39, 27),
                        StatusReservaEnum.ABERTA
                )),
                result
        );
    }

}
