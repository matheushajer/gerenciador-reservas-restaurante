package br.com.fiap.gerenciadorDeReservas.records.endereco;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe DTO para representar os dados vindo da API para criação
 * de um Endereco
 *
 * @param cep
 * @param logradouro
 * @param numero
 * @param complemento
 * @param bairro
 * @param cidade
 * @param uf
 */
public record DadosCriacaoEnderecoDTO(
        @NotBlank(message = "É obrigatório informar o cep do endereço")
        String cep,
        @NotBlank(message = "É obrigatório informar o logradouro do endereço")
        String logradouro,
        @NotBlank(message = "É obrigatório informar o número do endereço")
        String numero,
        String complemento,
        @NotBlank(message = "É obrigatório informar o bairro do endereço")
        String bairro,
        @NotBlank(message = "É obrigatório informar a cidade do endereço")
        String cidade,
        @NotBlank(message = "É obrigatório informar o uf do endereço")
        String uf

) {
}
