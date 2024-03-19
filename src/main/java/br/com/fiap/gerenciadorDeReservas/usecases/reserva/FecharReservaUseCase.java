package br.com.fiap.gerenciadorDeReservas.usecases.reserva;

import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para representar o caso de uso do fechamento de uma reserva.
 */
@Service
@Transactional(readOnly = true)
public class FecharReservaUseCase {

    @Autowired
    ReservaRepository reservaRepository;

    /**
     * Método para encerrar um reserva, atualizando o status dela para CANCELADO.
     *
     * @param reserva_id ID da reseva que será encerrada.
     */
    public void fecharReserva(Long reserva_id) {

        ReservaEntity reservaEntity = reservaRepository.findById(reserva_id).orElseThrow(
                () -> new EntityNotFoundException(("O reserva_id fornecido é inválido")));

        reservaEntity.setStatusReservaEnum(StatusReservaEnum.CANCELADA);

        reservaRepository.atualizarStatusReserva(reservaEntity.getId(), reservaEntity.getStatusReservaEnum());

    }


}
