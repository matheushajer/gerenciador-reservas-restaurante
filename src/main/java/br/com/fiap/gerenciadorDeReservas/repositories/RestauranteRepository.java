package br.com.fiap.gerenciadorDeReservas.repositories;

import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
    List<RestauranteEntity> findByNomeContainingIgnoreCase(String nomeRestaurante);
}
