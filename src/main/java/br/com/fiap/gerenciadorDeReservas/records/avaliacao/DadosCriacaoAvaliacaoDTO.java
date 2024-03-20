package br.com.fiap.gerenciadorDeReservas.records.avaliacao;

public record DadosCriacaoAvaliacaoDTO(
        Long cliente_id,
        Long restaurante_id,
        Double nota,
        String comentario
) {
}
