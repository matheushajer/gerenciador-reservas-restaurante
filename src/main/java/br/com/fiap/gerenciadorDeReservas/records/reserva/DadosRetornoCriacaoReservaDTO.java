package br.com.fiap.gerenciadorDeReservas.records.reserva;

import br.com.fiap.gerenciadorDeReservas.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosRetornoCriacaoReservaDTO(
        String nomeRestauramte,
        DadosCriacaoEnderecoDTO enderecoRestaurante,
        LocalDateTime dataAgendamento

) {
}
