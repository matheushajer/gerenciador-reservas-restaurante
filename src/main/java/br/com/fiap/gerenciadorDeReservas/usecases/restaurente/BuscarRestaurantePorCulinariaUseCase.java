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
 * restaurantes pela culinaria.
 */
@Service
@Transactional(readOnly = true)
public class BuscarRestaurantePorCulinariaUseCase {

    @Autowired
    RestauranteAdapter restauranteAdapter;
    @Autowired
    RestauranteRepository restauranteRepository;

    /**
     * Método para buscar uma lista de RestauranteEntity pela culinaria
     * do restaurante.
     *
     * @param culinariaRestaurante String com o parametro que será usado na busca.
     * @return Lista de DadosConsultaRestauranteDTO com os resultados da busca.
     */
    public List<DadosConsultaRestauranteDTO> buscarRestaurantesPorCulinaria(String culinariaRestaurante) {

        List<RestauranteEntity> restauranteEntity = restauranteRepository.findByTipoCulinariaContainingIgnoreCase(
                culinariaRestaurante);

        return restauranteAdapter.converterEntityParaDadosConsultaRestauranteDTO(restauranteEntity);

    }

}
