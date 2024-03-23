package br.com.fiap.gerenciadorDeReservas.usecases.reserva;

import br.com.fiap.gerenciadorDeReservas.adapters.reserva.ReservaAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class CriarReservaUseCaseTest {
    @Mock
    ReservaAdapter reservaAdapter;
    @Mock
    ReservaRepository reservaRepository;
    @InjectMocks
    CriarReservaUseCase criarReservaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarReserva() throws IllegalAccessException {
        when(reservaAdapter.converterParaEntity(any()))
                .thenReturn(new ReservaEntity(StatusReservaEnum.ABERTA,
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 33, 59),
                        null, null));
        when(reservaAdapter.converterParaDadosRetornoDTO(any()))
                .thenReturn(new DadosRetornoCriacaoReservaDTO(
                        "nomeRestaurante",
                        new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 33, 59)
                ));
        when(reservaRepository.save(any()))
                .thenReturn(new ReservaEntity(StatusReservaEnum.ABERTA,
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 33, 59),
                        null, null));

        DadosRetornoCriacaoReservaDTO result = criarReservaUseCase.criarReserva(
                new DadosCriacaoReservaDTO(
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 33, 59),
                        1L,
                        1L
                )
        );
        Assertions.assertEquals(
                new DadosRetornoCriacaoReservaDTO(
                        "nomeRestaurante",
                        new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                        LocalDateTime.of(2024, Month.MARCH, 23, 8, 33, 59)
                ),
                result
        );
    }

}
