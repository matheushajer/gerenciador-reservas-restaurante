package br.com.fiap.gerenciadorDeReservas.records.restaurante;

import java.time.LocalDateTime;

/**
 * Classe DTO para representar os dados vindo da API para reserva
 * de um Restaurante
 *
 * @param status
 * @param dtareserva
 * @param idRestaurante
 */
public record DadosReservaRestauranteDTO(
        String status,
        LocalDateTime dtareserva,

        Long cpf,
        Long idRestaurante
) {
}
