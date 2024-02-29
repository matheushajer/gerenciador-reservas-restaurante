package br.com.fiap.gerenciadorDeReservas.records.restaurante;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;

import java.time.LocalTime;

/**
 * Classe DTO para representar os dados vindo da API para criação
 * de um Restaurante
 *
 * @param nome
 * @param endereco
 * @param tipoCulinaria
 * @param horarioDeAbertura
 * @param horarioDeFechamento
 * @param capacidade
 */
public record DadosCriacaoRestauranteDTO(
        String nome,
        DadosCriacaoEnderecoDTO endereco,
        TipoCulinariaEnum tipoCulinaria,
        LocalTime horarioDeAbertura,
        LocalTime horarioDeFechamento,
        Integer capacidade
) {
}
