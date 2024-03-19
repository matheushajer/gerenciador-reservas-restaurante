package br.com.fiap.gerenciadorDeReservas.usecases.reserva;

import br.com.fiap.gerenciadorDeReservas.adapters.reserva.ReservaAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para representar o caso de uso da criação de uma reserva.
 */
@Service
@Transactional(readOnly = true)
public class CriarReservaUseCase {

    @Autowired
    ReservaAdapter reservaAdapter;
    @Autowired
    ReservaRepository reservaRepository;

    /**
     * Método para efetuar a criação de uma reserva.
     *
     * @param dadosCriacaoReservaDTO Objeto com os dados para criação da reserva.
     * @return DadosRetornoCriacaoReservaDTO Objeto com os dados tratados.
     * @throws IllegalAccessException Exception lançada, em caso de horário não disponivel.
     */
    public DadosRetornoCriacaoReservaDTO criarReserva(DadosCriacaoReservaDTO dadosCriacaoReservaDTO) throws IllegalAccessException {

        ReservaEntity reservaEntity = reservaAdapter.converterParaEntity(dadosCriacaoReservaDTO);

        reservaRepository.save(reservaEntity);

        return reservaAdapter.converterParaDadosRetornoDTO(reservaEntity);

    }

}
