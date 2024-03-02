package br.com.fiap.gerenciadorDeReservas.adapters.endereco;

import br.com.fiap.gerenciadorDeReservas.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.endereco.DadosCriacaoEnderecoDTO;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados
 */
@Service
public class EnderecoAdapter {


    /**
     * Método para converter os dados vindo da API para um EnderecoEntity.
     *
     * @param dadosCriacaoEnderecoDTO Objeto DadosCriacaoEnderecoDTO a ser convertido.
     * @return Objeto EnderecoEntity resultante da conversão.
     */
    public EnderecoEntity conveterParaEntity(DadosCriacaoEnderecoDTO dadosCriacaoEnderecoDTO,
                                             RestauranteEntity restauranteEntity) {

        return new EnderecoEntity(
                dadosCriacaoEnderecoDTO.cep(),
                dadosCriacaoEnderecoDTO.logradouro(),
                dadosCriacaoEnderecoDTO.numero(),
                dadosCriacaoEnderecoDTO.complemento(),
                dadosCriacaoEnderecoDTO.bairro(),
                dadosCriacaoEnderecoDTO.cidade(),
                dadosCriacaoEnderecoDTO.uf(),
                restauranteEntity
        );

    }

    /**
     * Método, para efetuar a conversão, de um EnderecoEntity, para um
     * DadosCriacaoEnderecoDTO.
     *
     * @param enderecoEntity Objeto EnderecoEntity a ser convertido.
     * @return Objeto DadosCriacaoEnderecoDTO resultante da conversão.
     */
    public DadosCriacaoEnderecoDTO converterParaDTO(EnderecoEntity enderecoEntity) {

        return new DadosCriacaoEnderecoDTO(
                enderecoEntity.getCep(),
                enderecoEntity.getLogradouro(),
                enderecoEntity.getNumero(),
                enderecoEntity.getComplemento(),
                enderecoEntity.getBairro(),
                enderecoEntity.getCidade(),
                enderecoEntity.getUf()
        );

    }

}
