package br.com.fiap.gerenciadorDeReservas.records.endereco;

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
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf

) {
}
