package br.com.fiap.gerenciadorDeReservas.records.reserva;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;

import java.time.LocalDateTime;

/**
 * Classe para representar os dados que serão retornados ao pesquisar
 * as reservas de um restaurante.
 *
 * @param dataReserva
 * @param statusReserva
 */
public record DadosConsultaReservasDTO(

        LocalDateTime dataReserva,
        StatusReservaEnum statusReserva

) {
}
