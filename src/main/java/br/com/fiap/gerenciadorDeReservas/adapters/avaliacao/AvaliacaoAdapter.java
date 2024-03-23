package br.com.fiap.gerenciadorDeReservas.adapters.avaliacao;

import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Método para converter os dados de uma AvaliacaoEntity para uma lista de objetos
     * DadosConsultaReservasDTO.
     *
     * @param avaliacaoEntity Objeto com os dados a serem convertidos.
     * @return Page<DadosConsultaReservasDTO> Objeto com os dados tratados para exibição.
     */
    public Page<DadosRetornoCriacaoAvaliacaoDTO> converterParaListaDadosRetornoDTO(Page<AvaliacaoEntity> avaliacaoEntity) {

        List<DadosRetornoCriacaoAvaliacaoDTO> dadosConsultaAvaliacoes = new ArrayList<>();

        avaliacaoEntity.forEach(avaliacao -> {
            DadosRetornoCriacaoAvaliacaoDTO dadosConsulta = new DadosRetornoCriacaoAvaliacaoDTO(
                    avaliacao.getAutor(),
                    avaliacao.getNota(),
                    avaliacao.getComentario()
            );

            dadosConsultaAvaliacoes.add(dadosConsulta);

        });

        return new PageImpl<>(dadosConsultaAvaliacoes, avaliacaoEntity.getPageable(), avaliacaoEntity.getTotalElements());

    }

}
