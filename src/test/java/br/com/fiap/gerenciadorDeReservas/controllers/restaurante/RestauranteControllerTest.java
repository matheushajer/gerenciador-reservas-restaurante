package br.com.fiap.gerenciadorDeReservas.controllers.restaurante;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorCidadeUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorCulinariaUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.BuscarRestaurantePorNomeUseCase;
import br.com.fiap.gerenciadorDeReservas.usecases.restaurente.CriarRestauranteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;

class RestauranteControllerTest {
    @Mock
    CriarRestauranteUseCase criarRestauranteUseCase;
    @Mock
    BuscarRestaurantePorNomeUseCase buscarRestaurantePorNomeUseCase;
    @Mock
    BuscarRestaurantePorCidadeUseCase buscarRestaurantePorCidadeUseCase;
    @Mock
    BuscarRestaurantePorCulinariaUseCase buscarRestaurantePorCulinariaUseCase;
    @InjectMocks
    RestauranteController restauranteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarRestaurantesPorNome() {
        when(buscarRestaurantePorNomeUseCase.buscarRestaurantesPorNome(anyString()))
                .thenReturn(
                        List.of(new DadosConsultaRestauranteDTO(
                                "nomeRestaurante",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13)
                        ))
                );

        ResponseEntity<List<DadosConsultaRestauranteDTO>> result =
                restauranteController.buscarRestaurantesPorNome("nomeRestaurante");

        Assertions.assertEquals(
                new ResponseEntity<List<DadosConsultaRestauranteDTO>>(
                        List.of(new DadosConsultaRestauranteDTO(
                                "nomeRestaurante",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13)
                        )),
                        null,
                        200
                ),
                result
        );
    }

    @Test
    void testBuscarRestaurantesPorCidade() {
        when(buscarRestaurantePorCidadeUseCase.buscarRestaurantesPorCidade(anyString()))
                .thenReturn(
                        List.of(new DadosConsultaRestauranteDTO(
                                "nomeRestaurante",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13)
                        ))
                );

        ResponseEntity<List<DadosConsultaRestauranteDTO>> result =
                restauranteController.buscarRestaurantesPorCidade("cidadeRestaurante");

        Assertions.assertEquals(
                new ResponseEntity<List<DadosConsultaRestauranteDTO>>(
                        List.of(new DadosConsultaRestauranteDTO(
                                "nomeRestaurante",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13)
                        )),
                        null,
                        200
                ),
                result
        );
    }

    @Test
    void testBuscarRestaurantesPorCulinaria() {
        when(buscarRestaurantePorCulinariaUseCase.buscarRestaurantesPorCulinaria(anyString()))
                .thenReturn(
                        List.of(new DadosConsultaRestauranteDTO(
                                "nomeRestaurante",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13)
                        ))
                );

        ResponseEntity<List<DadosConsultaRestauranteDTO>> result =
                restauranteController.buscarRestaurantesPorCulinaria("culinariaRestaurante");

        Assertions.assertEquals(
                new ResponseEntity<List<DadosConsultaRestauranteDTO>>(
                        List.of(new DadosConsultaRestauranteDTO(
                                "nomeRestaurante",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13)
                        )),
                        null,
                        200
                ),
                result
        );
    }

    @Test
    void testCriarRestaurante() {
        when(criarRestauranteUseCase.criarRestaurante(any()))
                .thenReturn(
                        new DadosCriacaoRestauranteDTO(
                                "nome",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13),
                                0
                        )
                );

        ResponseEntity<DadosCriacaoRestauranteDTO> result =
                restauranteController.criarRestaurante(
                        new DadosCriacaoRestauranteDTO(
                                "nome",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13),
                                0
                        )
                );

        Assertions.assertEquals(
                new ResponseEntity<DadosCriacaoRestauranteDTO>(
                        new DadosCriacaoRestauranteDTO(
                                "nome",
                                new DadosCriacaoEnderecoDTO("cep", "logradouro", "numero", "complemento", "bairro", "cidade", "uf"),
                                TipoCulinariaEnum.JAPONESA,
                                LocalTime.of(8, 27, 13),
                                LocalTime.of(8, 27, 13),
                                0
                        ),
                        null,
                        200
                ),
                result
        );
    }

}
