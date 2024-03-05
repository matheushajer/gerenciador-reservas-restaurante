package br.com.fiap.gerenciadorDeReservas.adaptersTest.endereco;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Classe para testar os métodos da classe EnderecoAdapter
 */
public class EnderecoAdapterTest {

    /**
     * Método para testar o método EnderecoAdapter#converterParaEntity
     */
    @Test
    public void deveConverterParaEntity() {

        // Mock da classe DadosCriacaoEnderecoDTO
        DadosCriacaoEnderecoDTO dadosCriacaoEnderecoDTO = new DadosCriacaoEnderecoDTO(
                "12345-678",
                "Rua Test",
                "123",
                "Complemento Test",
                "Bairro Test",
                "Cidade Test",
                "UF Test"
        );

        RestauranteEntity restauranteEntityMock = mock(RestauranteEntity.class);

        // Criação do Objeto EnderecoAdapter
        EnderecoAdapter enderecoAdapter = new EnderecoAdapter();

        // Método testado
        EnderecoEntity resultado = enderecoAdapter.conveterParaEntity(dadosCriacaoEnderecoDTO, restauranteEntityMock);

        // Validação dos resultados
        assertEquals("12345-678", resultado.getCep());
        assertEquals("Rua Test", resultado.getLogradouro());
        assertEquals("123", resultado.getNumero());
        assertEquals("Complemento Test", resultado.getComplemento());
        assertEquals("Bairro Test", resultado.getBairro());
        assertEquals("Cidade Test", resultado.getCidade());
        assertEquals("UF Test", resultado.getUf());
        assertEquals(restauranteEntityMock, resultado.getRestauranteEntity());

    }

    /**
     * Método para testar o método EnderecoAdapter#converterParaDTO
     */
    @Test
    public void deveConverterParaDTO() {

        // Mock da classe EnderecoEntity
        EnderecoEntity enderecoEntityMock = mock(EnderecoEntity.class);

        when(enderecoEntityMock.getCep()).thenReturn("12345-678");
        when(enderecoEntityMock.getLogradouro()).thenReturn("Rua Test");
        when(enderecoEntityMock.getNumero()).thenReturn("123");
        when(enderecoEntityMock.getComplemento()).thenReturn("Complemento Test");
        when(enderecoEntityMock.getBairro()).thenReturn("Bairro Test");
        when(enderecoEntityMock.getCidade()).thenReturn("Cidade Test");
        when(enderecoEntityMock.getUf()).thenReturn("UF Test");

        // Criação do Objeto EnderecoAdapter
        EnderecoAdapter enderecoAdapter = new EnderecoAdapter();

        // Método testado
        DadosCriacaoEnderecoDTO resultado = enderecoAdapter.converterParaDTO(enderecoEntityMock);

        // Validação dos resultados
        assertEquals("12345-678", resultado.cep());
        assertEquals("Rua Test", resultado.logradouro());
        assertEquals("123", resultado.numero());
        assertEquals("Complemento Test", resultado.complemento());
        assertEquals("Bairro Test", resultado.bairro());
        assertEquals("Cidade Test", resultado.cidade());
        assertEquals("UF Test", resultado.uf());

    }

}
