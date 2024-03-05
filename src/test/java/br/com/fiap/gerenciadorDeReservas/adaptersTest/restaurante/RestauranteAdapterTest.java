package br.com.fiap.gerenciadorDeReservas.adaptersTest.restaurante;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Classe para testar os métodos da classe RestauranteAdapter
 */
@SpringBootTest
public class RestauranteAdapterTest {

    @MockBean
    EnderecoAdapter enderecoAdapter;

    @Autowired
    RestauranteAdapter restauranteAdapter;

    /**
     * Método para testar o método RestauranteAdapter#converterParaEntity
     */
    @Test
    public void testConverterParaEntity() {

        // Mock da classe DadosCriacaoRestauranteDTO
        DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO = new DadosCriacaoRestauranteDTO(
                "Restaurante Test",
                new DadosCriacaoEnderecoDTO(
                        "12345-678",
                        "Rua Test",
                        "123",
                        "Complemento Test",
                        "Bairro Test",
                        "Cidade Test",
                        "UF Test"
                ),
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(17, 0, 0),
                LocalTime.of(23, 0, 0),
                5
        );

        // Mock EnderecoEntity
        EnderecoEntity enderecoEntityMock = new EnderecoEntity();
        when(enderecoAdapter.conveterParaEntity(any(), any())).thenReturn(enderecoEntityMock);

        // Chama o método que será testado
        RestauranteEntity resultado = restauranteAdapter.converterParaEntity(dadosCriacaoRestauranteDTO);

        // Verificação dos resultados esperados
        assertEquals("Restaurante Test", resultado.getNome());
        assertEquals(TipoCulinariaEnum.JAPONESA, resultado.getTipoCulinaria());
        assertEquals(LocalTime.of(17, 0, 0), resultado.getHorarioDeAbertura());
        assertEquals(LocalTime.of(23, 0, 0), resultado.getHorarioDeFechamento());
        assertEquals(5, resultado.getCapacidade());
        assertEquals(enderecoEntityMock, resultado.getEndereco());

    }

    /**
     * Método para testar o método RestauranteAdapter#converterParaDTO
     */
    @Test
    public void testConverterParaDTO() {

        // Mock dados RestauranteEntity
        RestauranteEntity restauranteEntityMock = new RestauranteEntity(
                "Restaurante Test",
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(17, 0, 0),
                LocalTime.of(23, 0, 0),
                5
        );

        // Mock dados DadosCriacaoEnderecoDTO
        DadosCriacaoEnderecoDTO enderecoDtoMock = new DadosCriacaoEnderecoDTO(
                "12345-678",
                "Rua Test",
                "123",
                "Complemento Test",
                "Bairro Test",
                "Cidade Test",
                "UF Test"
        );

        when(enderecoAdapter.converterParaDTO(any())).thenReturn(enderecoDtoMock);
        restauranteEntityMock.setEnderecoEntity(enderecoAdapter.conveterParaEntity(
                enderecoDtoMock, restauranteEntityMock));

        // Chama o método que será testado
        DadosCriacaoRestauranteDTO resultado = restauranteAdapter.converterParaDTO(restauranteEntityMock);

        // Verificação dos resultados
        assertEquals("Restaurante Test", resultado.nome());
        assertEquals(TipoCulinariaEnum.JAPONESA, resultado.tipoCulinaria());
        assertEquals(LocalTime.of(17, 0, 0), resultado.horarioDeAbertura());
        assertEquals(LocalTime.of(23, 0, 0), resultado.horarioDeFechamento());
        assertEquals(5, resultado.capacidade());
        assertEquals(enderecoDtoMock, resultado.endereco());

    }

    @Test
    public void testConverterEntityParaDadosConsultaRestauranteDTO() {

        List<RestauranteEntity> restauranteEntityList = new ArrayList<>();

        // Mock dados RestauranteEntity
        RestauranteEntity restauranteEntityMock1 = new RestauranteEntity(
                "Restaurante Test 1",
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(17, 0, 0),
                LocalTime.of(23, 0, 0),
                5
        );
        EnderecoEntity enderecoEntity1 = new EnderecoEntity();
        restauranteEntityMock1.setEnderecoEntity(enderecoEntity1);

        RestauranteEntity restauranteEntityMock2 = new RestauranteEntity(
                "Restaurante Test 2",
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(18, 0, 0),
                LocalTime.of(23, 0, 0),
                5
        );
        EnderecoEntity enderecoEntity2 = new EnderecoEntity();
        restauranteEntityMock2.setEnderecoEntity(enderecoEntity2);

        // Mock dados DadosCriacaoEnderecoDTO
        DadosCriacaoEnderecoDTO enderecoDtoMock = new DadosCriacaoEnderecoDTO(
                "12345-678",
                "Rua Test",
                "123",
                "Complemento Test",
                "Bairro Test",
                "Cidade Test",
                "UF Test"
        );


        when(enderecoAdapter.converterParaDTO(any())).thenReturn(enderecoDtoMock);


        restauranteEntityList.add(restauranteEntityMock1);

        restauranteEntityList.add(restauranteEntityMock2);

        // Chama o método que será testado
        List<DadosConsultaRestauranteDTO> resultado = restauranteAdapter.converterEntityParaDadosConsultaRestauranteDTO(
                restauranteEntityList);

        // Verificação dos resultados esperados
        assertEquals(2, resultado.size());

        assertEquals("Restaurante Test 1", resultado.get(0).nomeRestaurante());
        assertEquals(TipoCulinariaEnum.JAPONESA, resultado.get(0).culinaria());
        assertEquals(LocalTime.of(17, 0, 0), resultado.get(0).horarioDeAbertura());
        assertEquals(LocalTime.of(23, 0, 0), resultado.get(0).horarioDeFechamento());
        assertEquals(enderecoDtoMock, resultado.get(0).enderecoRestaurante());

        assertEquals("Restaurante Test 2", resultado.get(1).nomeRestaurante());
        assertEquals(TipoCulinariaEnum.JAPONESA, resultado.get(1).culinaria());
        assertEquals(LocalTime.of(18, 0, 0), resultado.get(1).horarioDeAbertura());
        assertEquals(LocalTime.of(23, 0, 0), resultado.get(1).horarioDeFechamento());
        assertEquals(enderecoDtoMock, resultado.get(1).enderecoRestaurante());

    }

}
