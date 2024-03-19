package br.com.fiap.gerenciadorDeReservas.usecases.reserva;

import br.com.fiap.gerenciadorDeReservas.adapters.reserva.ReservaAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosConsultaReservasDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe para representar o caso de uso de listar as reservas de um restaurante.
 */
@Service
@Transactional(readOnly = true)
public class ListarReservasPorRestauranteUseCase {

    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ReservaAdapter reservaAdapter;

    /**
     * Método para listar todas as reservas de um determinado restaurante.
     *
     * @param restaurante_id ID do Restaurante que terá as reservas listadas.
     * @return DadosConsultaReservasDTO Objeto com os dados tratados para exibição da reserva.
     */
    public List<DadosConsultaReservasDTO> listarReservasPorRestaurante(Long restaurante_id) {

        List<ReservaEntity> reservaEntity = reservaRepository.findByRestauranteEntityId(restaurante_id);

        return reservaAdapter.converterParaDadosConsultaPorRestaurante(reservaEntity);

    }


}
