package br.com.fiap.gerenciadorDeReservas.adapters.restaurante;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import org.junit.jupiter.api.Assertions;
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
    void deveConverterParaEntity() {

        // Mockando o comportamento do enderecoAdapter
        when(enderecoAdapter.conveterParaEntity(any(), any())).thenReturn(
                new EnderecoEntity(
                        "cep", "logradouro", "numero", "complemento", "bairro",
                        "cidade", "uf", new RestauranteEntity(
                        "nome", TipoCulinariaEnum.JAPONESA,
                        LocalTime.of(12, 20, 23), LocalTime.of(12, 20, 23),
                        0)
                )
        );

        // Criando um objeto de exemplo DadosCriacaoRestauranteDTO
        DadosCriacaoRestauranteDTO restauranteDTO = new DadosCriacaoRestauranteDTO(
                "nome",
                new DadosCriacaoEnderecoDTO(
                        "cep", "logradouro", "numero", "complemento", "bairro",
                        "cidade", "uf"),
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(12, 20, 23), LocalTime.of(12, 20, 23),
                0
        );

        // Convertendo o DadosCriacaoRestauranteDTO para RestauranteEntity usando o restauranteAdapter
        RestauranteEntity result = restauranteAdapter.converterParaEntity(restauranteDTO);

        // Verificando se a conversão foi feita corretamente
        RestauranteEntity expectedResult = new RestauranteEntity(
                "nome", TipoCulinariaEnum.JAPONESA,
                LocalTime.of(12, 20, 23), LocalTime.of(12, 20, 23), 0
        );

        // Comparando os valores esperados com os valores obtidos
        assertEquals(expectedResult.getNome(), result.getNome());
        assertEquals(enderecoAdapter.conveterParaEntity(any(), any()), result.getEnderecoEntity());
        assertEquals(expectedResult.getTipoCulinaria(), result.getTipoCulinaria());
        assertEquals(expectedResult.getHorarioDeAbertura(), result.getHorarioDeAbertura());
        assertEquals(expectedResult.getHorarioDeFechamento(), result.getHorarioDeFechamento());
        assertEquals(expectedResult.getCapacidade(), result.getCapacidade());
    }


    /**
     * Método para testar o método RestauranteAdapter#converterParaDTO
     */
    @Test
    void deveConverterParaDTO() {

        // Mockando o comportamento do endereçoAdapter
        when(enderecoAdapter.converterParaDTO(any())).thenReturn(
                new DadosCriacaoEnderecoDTO(
                        "cep", "logradouro", "numero", "complemento",
                        "bairro", "cidade", "uf"));

        // Criando um restauranteEntity
        RestauranteEntity restauranteEntity = new RestauranteEntity(
                "nome", TipoCulinariaEnum.JAPONESA,
                LocalTime.of(12, 20, 23), LocalTime.of(12, 20, 23), 0);

        // Convertendo o restauranteEntity para DadosCriacaoRestauranteDTO usando o restauranteAdapter
        DadosCriacaoRestauranteDTO result = restauranteAdapter.converterParaDTO(restauranteEntity);

        // Verificando se a conversão foi feita corretamente
        DadosCriacaoRestauranteDTO expectedResult = new DadosCriacaoRestauranteDTO(
                "nome",
                new DadosCriacaoEnderecoDTO(
                        "cep", "logradouro", "numero", "complemento", "bairro",
                        "cidade", "uf"),
                TipoCulinariaEnum.JAPONESA,
                LocalTime.of(12, 20, 23), LocalTime.of(12, 20, 23),
                0);

        Assertions.assertEquals(expectedResult, result);
    }


    @Test
    public void deveConverterEntityParaDadosConsultaRestauranteDTO() {

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
