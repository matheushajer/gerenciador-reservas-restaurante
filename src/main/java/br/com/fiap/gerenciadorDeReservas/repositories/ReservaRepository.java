package br.com.fiap.gerenciadorDeReservas.repositories;

import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}
