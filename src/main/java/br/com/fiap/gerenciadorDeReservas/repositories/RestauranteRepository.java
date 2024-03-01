package br.com.fiap.gerenciadorDeReservas.repositories;

import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
    List<RestauranteEntity> findByNomeContainingIgnoreCase(String nomeRestaurante);
    List<RestauranteEntity> findByEnderecoEntity_CidadeContainingIgnoreCase(String cidadeRestaurante);

    @Query("SELECT r FROM RestauranteEntity r WHERE LOWER(r.tipoCulinaria) LIKE %:tipoCulinaria%")
    List<RestauranteEntity> findByTipoCulinariaContainingIgnoreCase(@Param("tipoCulinaria") String tipoCulinaria);

}
