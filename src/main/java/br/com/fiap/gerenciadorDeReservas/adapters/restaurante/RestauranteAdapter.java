package br.com.fiap.gerenciadorDeReservas.adapters.restaurante;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados
 */
@Service
public class RestauranteAdapter {

    @Autowired
    EnderecoAdapter enderecoAdapter;

    /**
     * Método para converter os dados vindo da API para um RestauranteEntity
     *
     * @param dadosCriacaoRestauranteDTO
     * @return RestauranteEntity
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
     * Método, para efetuar a conversão, de um RestauranteEntity, para um
     * DadosCriacaoRestauranteDTO
     *
     * @param restauranteEntity
     * @return DadosCriacaoRestauranteDTO
     */
    public DadosCriacaoRestauranteDTO converterParaDTO(RestauranteEntity restauranteEntity) {

        return new DadosCriacaoRestauranteDTO(
                restauranteEntity.getNome(),
                enderecoAdapter.converterParaDTO(restauranteEntity.getEndereco()),
                restauranteEntity.getTipoCulinaria(),
                restauranteEntity.getHorarioDeAbertura(),
                restauranteEntity.getHorarioDeFechamento(),
                restauranteEntity.getCapacidade()
        );

    }


}
