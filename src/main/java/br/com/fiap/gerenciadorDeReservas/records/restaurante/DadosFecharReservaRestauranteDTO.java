package br.com.fiap.gerenciadorDeReservas.records.restaurante;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe DTO para representar os dados vindo da API para reserva
 * de um Restaurante
 *
 * @param dtareserva
 * @param idRestaurante
 */
public record DadosFecharReservaRestauranteDTO(
        LocalDateTime dtareserva,
        Long cpf,
        Long idRestaurante
) {
}
