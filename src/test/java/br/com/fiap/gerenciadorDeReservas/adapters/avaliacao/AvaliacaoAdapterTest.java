package br.com.fiap.gerenciadorDeReservas.adapters.avaliacao;

import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AvaliacaoAdapterTest {
    AvaliacaoAdapter avaliacaoAdapter = new AvaliacaoAdapter();

    @Test
    void testConverterParaEntity() {
        // Criando entidades de cliente e restaurante
        ClienteEntity cliente = new ClienteEntity("nome", null, null);
        RestauranteEntity restaurante = new RestauranteEntity(
                "nome", TipoCulinariaEnum.JAPONESA, LocalTime.of(8, 51, 49),
                LocalTime.of(8, 51, 49), 0);

        // Criando DTO de criação de avaliação
        DadosCriacaoAvaliacaoDTO dadosAvaliacaoDTO = new DadosCriacaoAvaliacaoDTO(
                1L, 1L, (double) 0, "comentario");

        AvaliacaoEntity result = avaliacaoAdapter.converterParaEntity(cliente, restaurante, dadosAvaliacaoDTO);

        assertEquals("nome", result.getAutor());
        assertEquals(Double.valueOf(0), result.getNota());
        assertEquals("comentario", result.getComentario());
        assertEquals(restaurante, result.getRestauranteEntity());
    }


    @Test
    void testConverterParaDadosRetornoDTO() {
        DadosRetornoCriacaoAvaliacaoDTO result = avaliacaoAdapter
                .converterParaDadosRetornoDTO(new AvaliacaoEntity(
                        "autor", (double) 0, "comentario", null, null));

        assertEquals(new DadosRetornoCriacaoAvaliacaoDTO("autor", (double) 0, "comentario"), result);
    }
}
