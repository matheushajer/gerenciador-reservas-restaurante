package br.com.fiap.gerenciadorDeReservas.records.restaurante;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        @NotBlank(message = "O nome do Restaurante é obrigatório")
        String nome,
        @Valid
        DadosCriacaoEnderecoDTO endereco,
        @NotNull(message = "O tipo de culinária do Restaurante é obrigatória")
        TipoCulinariaEnum tipoCulinaria,
        @NotNull(message = "O horário de abertura do Restaurante é obrigatória")
        LocalTime horarioDeAbertura,
        @NotNull(message = "O horário de fechamento do Restaurante é obrigatório")
        LocalTime horarioDeFechamento,
        @NotNull(message = "A capacidade do Restaurante é obrigatória")
        Integer capacidade
) {
}
