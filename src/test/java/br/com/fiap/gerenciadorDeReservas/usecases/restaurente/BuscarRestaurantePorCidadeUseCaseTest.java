package br.com.fiap.gerenciadorDeReservas.usecases.restaurente;

import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;

class BuscarRestaurantePorCidadeUseCaseTest {
    @Mock
    RestauranteAdapter restauranteAdapter;
    @Mock
    RestauranteRepository restauranteRepository;
    @InjectMocks
    BuscarRestaurantePorCidadeUseCase buscarRestaurantePorCidadeUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarRestaurantesPorCidade() {

        when(restauranteAdapter.converterEntityParaDadosConsultaRestauranteDTO(any())).thenReturn(List.of(new DadosConsultaRestauranteDTO("nomeRestaurante", new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"), TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 34, 46), LocalTime.of(13, 34, 46))));
        when(restauranteRepository.findByEnderecoEntity_CidadeContainingIgnoreCase(anyString())).thenReturn(List.of(new RestauranteEntity("nome", TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 34, 46), LocalTime.of(13, 34, 46), Integer.valueOf(0))));

        List<DadosConsultaRestauranteDTO> result = buscarRestaurantePorCidadeUseCase.buscarRestaurantesPorCidade("cidadeRestaurante");
        Assertions.assertEquals(List.of(new DadosConsultaRestauranteDTO("nomeRestaurante", new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"), TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 34, 46), LocalTime.of(13, 34, 46))), result);
    }
}
