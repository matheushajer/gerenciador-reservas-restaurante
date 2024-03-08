package br.com.fiap.gerenciadorDeReservas.records.cliente;

import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;

/**
 * Classe DTO para representar os dados vindo da API para criação
 * de um Endereco
 *
 * @param cliente
 */
public record DadosCriacaoClienteDTO(
        ClienteEntity cliente

) {
}
