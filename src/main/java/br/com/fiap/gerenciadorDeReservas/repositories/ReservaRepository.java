package br.com.fiap.gerenciadorDeReservas.repositories;

import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    @Modifying
    @Query("UPDATE ReservaEntity r SET r.statusReservaEnum = :novoStatusReservaEnum WHERE r.id = :reserva_id")
    void atualizarStatusReserva(Long reserva_id, StatusReservaEnum novoStatusReservaEnum);

    List<ReservaEntity> findByRestauranteEntityId(Long restauranteId);

}
