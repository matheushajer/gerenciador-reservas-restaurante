package br.com.fiap.gerenciadorDeReservas.usecases.restaurente;

import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosConsultaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe para representar o caso de uso da busca de
 * restaurantes por nome.
 */
@Service
@Transactional(readOnly = true)
public class BuscarRestaurantePorNomeUseCase {

    @Autowired
    RestauranteAdapter restauranteAdapter;
    @Autowired
    RestauranteRepository restauranteRepository;

    /**
     * Método para buscar uma lista de RestauranteEntity pelo nome
     * do restaurante.
     *
     * @param nomeRestaurante String com o parametro que será usado na busca.
     * @return Lista de DadosConsultaRestauranteDTO com os resultados da busca.
     */
    public List<DadosConsultaRestauranteDTO> buscarRestaurantes(String nomeRestaurante) {

        List<RestauranteEntity> restauranteEntity = restauranteRepository.findByNomeContainingIgnoreCase(nomeRestaurante);

        return restauranteAdapter.converterEntityParaDadosConsultaRestauranteDTO(restauranteEntity);

    }

}
