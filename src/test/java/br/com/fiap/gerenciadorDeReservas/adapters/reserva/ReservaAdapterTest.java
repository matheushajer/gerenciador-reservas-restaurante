package br.com.fiap.gerenciadorDeReservas.adapters.reserva;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosConsultaReservasDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.util.ValidadorDeReservas;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ReservaAdapterTest {
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    RestauranteRepository restauranteRepository;
    @Mock
    EnderecoAdapter enderecoAdapter;
    @Mock
    ValidadorDeReservas agendamentoValidoUseCase;
    @InjectMocks
    ReservaAdapter reservaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConverterParaEntity_ComIDsValidos() throws IllegalAccessException {
        DadosCriacaoReservaDTO dadosReservaDTO = new DadosCriacaoReservaDTO(
                LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                1L,
                1L
        );

        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));

        RestauranteEntity restauranteEntity = new RestauranteEntity("nome", TipoCulinariaEnum.JAPONESA, LocalTime.of(9, 1, 17), LocalTime.of(9, 1, 17), 0);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));

        when(agendamentoValidoUseCase.isDataAgendamentoValido(eq(LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17)), eq(restauranteEntity)))
                .thenReturn(true);

        ReservaEntity result = reservaAdapter.converterParaEntity(dadosReservaDTO);

        assertEquals(StatusReservaEnum.ABERTA, result.getStatusReservaEnum());
        assertEquals(clienteEntity, result.getClienteEntity());
        assertEquals(restauranteEntity, result.getRestauranteEntity());
        assertEquals(LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17), result.getDataReserva());
    }

    @Test
    void testConverterParaEntity_ComIDsInvalidos() {
        DadosCriacaoReservaDTO dadosReservaDTO = new DadosCriacaoReservaDTO(
                LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                2L,
                2L
        );

        when(clienteRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        when(restauranteRepository.findById(2L)).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class, () -> reservaAdapter.converterParaEntity(dadosReservaDTO));
    }

    @Test
    void testConverterParaEntity_ClienteInvalido() {
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());

        DadosCriacaoReservaDTO dadosReservaDTO = new DadosCriacaoReservaDTO(
                LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                1L,
                1L
        );

        assertThrows(EntityNotFoundException.class,
                () -> reservaAdapter.converterParaEntity(dadosReservaDTO));
    }

    @Test
    void testConverterParaEntity_RestauranteInvalido() {
        when(restauranteRepository.findById(any())).thenReturn(Optional.empty());

        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));

        DadosCriacaoReservaDTO dadosReservaDTO = new DadosCriacaoReservaDTO(
                LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                1L,
                1L
        );

        assertThrows(EntityNotFoundException.class,
                () -> reservaAdapter.converterParaEntity(dadosReservaDTO));
    }

    @Test
    void testConverterParaEntity_DataReservaInvalida() throws IllegalAccessException {
        when(agendamentoValidoUseCase.isDataAgendamentoValido(any(), any())).thenReturn(false);

        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteEntity));

        RestauranteEntity restauranteEntity = new RestauranteEntity("nome", TipoCulinariaEnum.JAPONESA, LocalTime.of(9, 1, 17), LocalTime.of(9, 1, 17), 0);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restauranteEntity));

        DadosCriacaoReservaDTO dadosReservaDTO = new DadosCriacaoReservaDTO(
                LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                1L,
                1L
        );

        assertThrows(IllegalAccessException.class,
                () -> reservaAdapter.converterParaEntity(dadosReservaDTO));
    }

    @Test
    void testConverterParaDadosRetornoDTO() {
        when(enderecoAdapter.converterParaDTO(any()))
                .thenReturn(new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"));

        DadosRetornoCriacaoReservaDTO result = reservaAdapter.converterParaDadosRetornoDTO(
                new ReservaEntity(StatusReservaEnum.ABERTA,
                        LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                        new ClienteEntity("nome", "cpf", "email"),
                        new RestauranteEntity("nomeRestauramte", TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(9, 1, 17), LocalTime.of(9, 1, 17), 0)));
        assertEquals(
                new DadosRetornoCriacaoReservaDTO("nomeRestauramte",
                        new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                        LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17)),
                result);
    }

    @Test
    void testConverterParaDadosConsultaPorRestaurante() {
        List<DadosConsultaReservasDTO> result = reservaAdapter.converterParaDadosConsultaPorRestaurante(
                List.of(new ReservaEntity(StatusReservaEnum.ABERTA,
                        LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17),
                        new ClienteEntity("nome", "cpf", "email"),
                        new RestauranteEntity("nome", TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(9, 1, 17), LocalTime.of(9, 1, 17), 0))));

        assertEquals(
                List.of(new DadosConsultaReservasDTO(LocalDateTime.of(2024, Month.MARCH, 23, 9, 1, 17), StatusReservaEnum.ABERTA)),
                result);
    }

}
