package br.com.fiap.gerenciadorDeReservas.usecases.avaliacao;

import br.com.fiap.gerenciadorDeReservas.adapters.avaliacao.AvaliacaoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.AvaliacaoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe para representar o caso de uso da pesquisa
 * de avaliações, filtrando por restaurante.
 */
@Service
@Transactional(readOnly = true)
public class BuscarAvaliacoesPorRestauranteUseCase {

    @Autowired
    AvaliacaoRespository avaliacaoRespository;

    @Autowired
    AvaliacaoAdapter avaliacaoAdapter;

    public Page<DadosRetornoCriacaoAvaliacaoDTO> listarAvaliacoesPorRestaurante(Long restaurante_id, Pageable pageable) {

        Page<AvaliacaoEntity> avaliacaoEntity = avaliacaoRespository.findByRestauranteEntityId(restaurante_id, pageable);

        return avaliacaoAdapter.converterParaListaDadosRetornoDTO(avaliacaoEntity);

    }

}
