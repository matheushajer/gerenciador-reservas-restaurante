package br.com.fiap.gerenciadorDeReservas.usecases.reserva.util;

import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Classe para validar se um agendamento é valido ou não.
 */
@Service
@Transactional(readOnly = true)
public class ValidadorDeReservas {

    @Autowired
    RestauranteRepository restauranteRepository;

    /**
     * Método para validar se uma data de agendamento é valida, olhando a disponibilidade da capacidade e existencia
     * do intervalo de agendamento.
     *
     * @param dataAgendamento Objeto com a data e hora desejada para reserva.
     * @return true ou false
     */
    public boolean isDataAgendamentoValido(LocalDateTime dataAgendamento, RestauranteEntity restauranteEntity) {

        if (restauranteEntity.getDiasDeOperacao().contains(dataAgendamento) && restauranteEntity.getCapacidade() > 0) {

            restauranteEntity.setCapacidade(restauranteEntity.getCapacidade() - 1);

            restauranteRepository.atualizarCapacidade(restauranteEntity.getId(), restauranteEntity.getCapacidade());

            return true;
        } else {
            return false;
        }

    }

}
