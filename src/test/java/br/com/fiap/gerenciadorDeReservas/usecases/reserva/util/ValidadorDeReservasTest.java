package br.com.fiap.gerenciadorDeReservas.usecases.reserva.util;

import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorDeReservasTest {
    @Mock
    RestauranteRepository restauranteRepository;
    @InjectMocks
    ValidadorDeReservas validadorDeReservas;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsDataAgendamentoValido() {

        RestauranteEntity restauranteEntity = new RestauranteEntity(
                "nome",
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(8, 40, 35),
                LocalTime.of(9, 40, 35),
                1
        );

        restauranteEntity.setDiasDeOperacao();

        LocalDateTime dataAgendamento = LocalDateTime.of(2024, Month.MAY, 24, 9, 40, 35);

        boolean result = validadorDeReservas.isDataAgendamentoValido(dataAgendamento, restauranteEntity);

        assertTrue(result);
    }

    @Test
    void testIsDataAgendamentoValidoReturnFalse() {

        RestauranteEntity restauranteEntity = new RestauranteEntity(
                "nome",
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(8, 40, 35),
                LocalTime.of(9, 40, 35),
                1
        );

        restauranteEntity.setDiasDeOperacao();

        LocalDateTime dataAgendamento = LocalDateTime.of(2024, Month.MAY, 24, 9, 20, 35);

        boolean result = validadorDeReservas.isDataAgendamentoValido(dataAgendamento, restauranteEntity);

        assertFalse(result);
    }


}
