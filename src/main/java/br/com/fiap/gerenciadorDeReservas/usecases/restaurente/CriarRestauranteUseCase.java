package br.com.fiap.gerenciadorDeReservas.usecases.restaurente;

import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para representar o caso de uso da criação de um restaurante
 */
@Service
@Transactional(readOnly = true)
public class CriarRestauranteUseCase {

    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    RestauranteAdapter restauranteAdapter;

    /**
     * Método para efetuar a criação de uma entity RestauranteEntity e gravar no banco.
     *
     * @param dadosCriacaoRestauranteDTO Objeto DadosCriacaoRestauranteDTO com os dados de criação do RestauranteEntity.
     * @return Objeto DadosCriacaoRestauranteDTO com os dados gravados.
     */
    public DadosCriacaoRestauranteDTO criarRestaurante(DadosCriacaoRestauranteDTO dadosCriacaoRestauranteDTO) {

        RestauranteEntity restauranteEntity = restauranteAdapter.converterParaEntity(dadosCriacaoRestauranteDTO);

        restauranteRepository.save(restauranteEntity);

        return restauranteAdapter.converterParaDTO(restauranteEntity);

    }

}
