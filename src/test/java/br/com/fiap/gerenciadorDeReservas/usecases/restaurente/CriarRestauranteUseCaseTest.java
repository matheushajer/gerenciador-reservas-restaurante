package br.com.fiap.gerenciadorDeReservas.usecases.restaurente;

import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

import static org.mockito.Mockito.*;

class CriarRestauranteUseCaseTest {
    @Mock
    RestauranteRepository restauranteRepository;
    @Mock
    RestauranteAdapter restauranteAdapter;
    @InjectMocks
    CriarRestauranteUseCase criarRestauranteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarRestaurante() {

        when(restauranteRepository.save(any())).thenReturn(new RestauranteEntity("nome", TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 11, 16), LocalTime.of(13, 11, 16), 0));
        when(restauranteAdapter.converterParaEntity(any())).thenReturn(new RestauranteEntity("nome", TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 11, 16), LocalTime.of(13, 11, 16), 0));
        when(restauranteAdapter.converterParaDTO(any())).thenReturn(new DadosCriacaoRestauranteDTO("nome", new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"), TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 11, 16), LocalTime.of(13, 11, 16), 0));

        DadosCriacaoRestauranteDTO result = criarRestauranteUseCase.criarRestaurante(new DadosCriacaoRestauranteDTO("nome", new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"), TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 11, 16), LocalTime.of(13, 11, 16), 0));

        Assertions.assertEquals(new DadosCriacaoRestauranteDTO("nome", new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"), TipoCulinariaEnum.JAPONESA, LocalTime.of(13, 11, 16), LocalTime.of(13, 11, 16), 0), result);

    }
}
