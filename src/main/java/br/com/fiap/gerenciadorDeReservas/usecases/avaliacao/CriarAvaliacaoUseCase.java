package br.com.fiap.gerenciadorDeReservas.usecases.avaliacao;

import br.com.fiap.gerenciadorDeReservas.adapters.avaliacao.AvaliacaoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.AvaliacaoRespository;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para representar o caso de uso da criação
 * de avaliações dos restaurantes,  feita pelo cliente.
 */
@Service
@Transactional(readOnly = true)
public class CriarAvaliacaoUseCase {

    @Autowired
    AvaliacaoRespository avaliacaoRespository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    AvaliacaoAdapter avaliacaoAdapter;

    /**
     * Método para efetuar a criação de uma nova avaliação para um determinado restaurante.
     *
     * @param dadosCriacaoAvaliacaoDTO Objeto com os dados para criação da avaliação.
     * @return DadosRetornoCriacaoAvaliacaoDTO Objeto com os dados tratados para retorno.
     */
    public DadosRetornoCriacaoAvaliacaoDTO criarAvaliacao(DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO) {

        ClienteEntity clienteEntity = clienteRepository.findById(dadosCriacaoAvaliacaoDTO.cliente_id()).orElseThrow(
                () -> new EntityNotFoundException(("O cliente_id fornecido é inválido")));

        RestauranteEntity restauranteEntity = restauranteRepository.findById(dadosCriacaoAvaliacaoDTO.restaurante_id())
                .orElseThrow(() -> new EntityNotFoundException(("O cliente_id fornecido é inválido")));

        AvaliacaoEntity avaliacaoEntity = avaliacaoAdapter.converterParaEntity(clienteEntity, restauranteEntity,
                dadosCriacaoAvaliacaoDTO);

        avaliacaoRespository.save(avaliacaoEntity);

        return avaliacaoAdapter.converterParaDadosRetornoDTO(avaliacaoEntity);

    }

}
