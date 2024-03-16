package br.com.fiap.gerenciadorDeReservas.records.telefone;

import jakarta.validation.constraints.NotNull;

/**
 * Classe DTO para representar os dados vindo da API para criação
 * de um Telefone.
 *
 * @param ddi
 * @param ddd
 * @param numero
 */
public record DadosCriacaoTelefoneDTO(
        @NotNull(message = "Obrigatório informar o ddi do telefone")
        int ddi,
        @NotNull(message = "Obrigatório informar o ddd do telefone")
        int ddd,
        @NotNull(message = "Obrigatório informar o numero do telefone")
        int numero
) {
}
