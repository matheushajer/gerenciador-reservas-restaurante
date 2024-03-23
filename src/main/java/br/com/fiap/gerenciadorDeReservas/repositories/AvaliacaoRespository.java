package br.com.fiap.gerenciadorDeReservas.repositories;

import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRespository extends JpaRepository<AvaliacaoEntity, Long> {

    Page<AvaliacaoEntity> findByRestauranteEntityId(Long restauranteId, Pageable pageable);

}
