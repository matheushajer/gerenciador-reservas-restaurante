package br.com.fiap.gerenciadorDeReservas.adapters.restaurante;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados
 */
@Service
public class RestauranteAdapter {

    @Autowired
    EnderecoAdapter enderecoAdapter;

    /**
     * Metodo para converter os dados vindo da API para um RestauranteEntity
     *
     * @param dadosCriacaoRestauranteDTO Objeto DadosCriacaoRestauranteDTO a ser convertido
     * @return Obejto RestauranteEntity resultante da conversão
     */
    public RestauranteEntity converterParaEntity(DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO) {

        RestauranteEntity restauranteEntity = new RestauranteEntity(
                dadosCriacaoRestauranteDTO.nome(),
                dadosCriacaoRestauranteDTO.tipoCulinaria(),
                dadosCriacaoRestauranteDTO.horarioDeAbertura(),
                dadosCriacaoRestauranteDTO.horarioDeFechamento(),
                dadosCriacaoRestauranteDTO.capacidade()
        );

        restauranteEntity.setEnderecoEntity(enderecoAdapter.conveterParaEntity(dadosCriacaoRestauranteDTO.endereco(),
                restauranteEntity));

        return restauranteEntity;

    }

    /**
     * Método para efetuar a conversão, de um RestauranteEntity, para um
     * DadosCriacaoRestauranteDTO.
     *
     * @param restauranteEntity Objeto RestauranteEntity a ser convertido.
     * @return Objeto DadosCriacaoRestauranteDTO resultante da conversão.
     */
    public DadosCriacaoRestauranteDTO converterParaDTO(RestauranteEntity restauranteEntity) {

        return new DadosCriacaoRestauranteDTO(
                restauranteEntity.getNome(),
                enderecoAdapter.converterParaDTO(restauranteEntity.getEnderecoEntity()),
                restauranteEntity.getTipoCulinaria(),
                restauranteEntity.getHorarioDeAbertura(),
                restauranteEntity.getHorarioDeFechamento(),
                restauranteEntity.getCapacidade()
        );

    }


    /**
     * Método para converter um RestauranteEntity para objeto do tipo
     * DadosConsultaRestauranteDTO.
     *
     * @param restauranteEntity Lista de objetos RestauranteEntity a serem convertidos.
     * @return Lista de objetos DadosConsultaRestauranteDTO resultante da conversão.
     */
    public List<DadosConsultaRestauranteDTO> converterEntityParaDadosConsultaRestauranteDTO(
            List<RestauranteEntity> restauranteEntity) {

        List<DadosConsultaRestauranteDTO> dadosRestaurantes = new ArrayList<>();

        for (RestauranteEntity restaurante : restauranteEntity) {
            DadosConsultaRestauranteDTO dadosRestaurante = new DadosConsultaRestauranteDTO(
                    restaurante.getNome(),
                    enderecoAdapter.converterParaDTO(restaurante.getEnderecoEntity()),
                    restaurante.getTipoCulinaria(),
                    restaurante.getHorarioDeAbertura(),
                    restaurante.getHorarioDeFechamento()
            );
            dadosRestaurantes.add(dadosRestaurante);
        }

        return dadosRestaurantes;

    }

}
