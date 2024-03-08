package br.com.fiap.gerenciadorDeReservas.repositories;

import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.statusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

    @Query("SELECT COUNT(r) FROM ReservaEntity r WHERE r.idrestaurante = :idrestaurante AND r.reservadthora >= :dataInicio AND r.reservadthora < :dataFim")
    int countReservasByRestauranteAndData(@Param("idrestaurante") Long idrestaurante, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
    @Query("SELECT r FROM ReservaEntity r LEFT JOIN FETCH r.telefone LEFT JOIN FETCH r.cliente WHERE r.idrestaurante = :idrestaurante")
    List<ReservaEntity> findByidRestaurante(@Param("idrestaurante") Long idrestaurante);

    List<ReservaEntity> findByClienteAndIdrestauranteAndReservadthoraAndStatusEnum(
            ClienteEntity cliente,
            Long idRestaurante,
            LocalDateTime data,
            statusEnum status
    );

    List<ReservaEntity> findByIdrestauranteAndReservadthoraAndStatusEnum(
            Long idRestaurante,
            LocalDateTime data,
            statusEnum status
    );
    List<ReservaEntity> findByIdrestauranteAndStatusEnum(
            Long idRestaurante,
            statusEnum status
    );

    List<ReservaEntity> findByClienteAndReservadthoraAndStatusEnum(
            ClienteEntity cliente,
            LocalDateTime data,
            statusEnum status
    );

    List<ReservaEntity> findByClienteAndStatusEnum(
            ClienteEntity cliente,
            statusEnum status
    );
}
