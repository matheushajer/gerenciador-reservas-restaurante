package br.com.fiap.gerenciadorDeReservas.records.reserva;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCriacaoReservaDTO(
        @NotNull(message = "Data do agendamento obrigatória!")
        LocalDateTime dataReserva,
        @NotNull(message = "ID cliente obrigatório")
        Long cliente_id,
        @NotNull(message = "ID restaurante obrigatório")
        Long restaurante_id
) {
}
