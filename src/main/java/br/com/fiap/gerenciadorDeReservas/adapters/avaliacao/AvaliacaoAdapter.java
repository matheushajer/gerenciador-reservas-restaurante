package br.com.fiap.gerenciadorDeReservas.adapters.avaliacao;

import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados.
 */
@Service
public class AvaliacaoAdapter {

    /**
     * Método para converter os dados vindo da API para um Objeto AvaliacaoEntity.
     *
     * @param clienteEntity            Obejto com os dados do cliente que criou a avaliação.
     * @param restauranteEntity        Obejto com os dados do restaurante que recebeu a avaliação.
     * @param dadosCriacaoAvaliacaoDTO Objeto com os dados a serem tratados.
     * @return AvaliacaoEntity Objeto com os dados tratados.
     */
    public AvaliacaoEntity converterParaEntity(
            ClienteEntity clienteEntity, RestauranteEntity restauranteEntity,
            DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO) {

        LocalDateTime dateAgora = LocalDateTime.now();

        return new AvaliacaoEntity(
                clienteEntity.getNome(),
                dadosCriacaoAvaliacaoDTO.nota(),
                dadosCriacaoAvaliacaoDTO.comentario(),
                dateAgora,
                restauranteEntity
        );

    }

    /**
     * Método para converter um objeto AvaliacaoEntity, para um Objeto DadosRetornoCriacaoAvaliacaoDTO e devolver
     * pela API.
     *
     * @param avaliacaoEntity Objeto com os dados da avaliação.
     * @return DadosRetornoCriacaoAvaliacaoDTO Objeto com os dados tratados para retorno.
     */
    public DadosRetornoCriacaoAvaliacaoDTO converterParaDadosRetornoDTO(AvaliacaoEntity avaliacaoEntity) {

        return new DadosRetornoCriacaoAvaliacaoDTO(
                avaliacaoEntity.getAutor(),
                avaliacaoEntity.getNota(),
                avaliacaoEntity.getComentario()
        );

    }

}
