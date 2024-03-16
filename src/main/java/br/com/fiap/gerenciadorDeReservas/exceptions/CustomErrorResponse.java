package br.com.fiap.gerenciadorDeReservas.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe para reprensetar uma mensagem erro, após
 * uma chamada de API inválida.
 */
@Data
public class CustomErrorResponse {

    private LocalDateTime timestamp;
    private String campo;
    private String mensagem;
    private int status;

    public CustomErrorResponse(LocalDateTime timestamp, String campo, String mensagem, int status) {
        this.timestamp = timestamp;
        this.campo = campo;
        this.mensagem = mensagem;
        this.status = status;
    }

}
