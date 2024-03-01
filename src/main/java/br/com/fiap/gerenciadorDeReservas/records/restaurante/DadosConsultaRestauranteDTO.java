package br.com.fiap.gerenciadorDeReservas.records.restaurante;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;

import java.time.LocalTime;

/**
 * Classe para representar o dados retornados ao consultar um Restaurante
 * pela API
 */
public record DadosConsultaRestauranteDTO(
        String nomeRestaurante,
        DadosCriacaoEnderecoDTO enderecoRestaurante,
        TipoCulinariaEnum culinaria,
        LocalTime horarioDeAbertura,
        LocalTime horarioDeFechamento
) {
}
