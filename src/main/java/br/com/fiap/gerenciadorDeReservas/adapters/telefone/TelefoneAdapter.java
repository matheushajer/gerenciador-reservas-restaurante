package br.com.fiap.gerenciadorDeReservas.adapters.telefone;

import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDeReservas.records.telefone.DadosCriacaoTelefoneDTO;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados.
 */
@Service
public class TelefoneAdapter {

    /**
     * Método para converter os dados vindo da API para um TelefoneEntity.
     *
     * @param dadosCriacaoTelefoneDTO Objeto DadosCriacaoTelefoneDTO a ser convertido.
     * @param clienteEntity           Obejto ClienteEntity a ser vinculado.
     * @return Objeto TelefoneEntity resultante da conversão.
     */
    public TelefoneEntity converterParaEntity(DadosCriacaoTelefoneDTO dadosCriacaoTelefoneDTO,
                                              ClienteEntity clienteEntity) {

        return new TelefoneEntity(
                dadosCriacaoTelefoneDTO.ddi(),
                dadosCriacaoTelefoneDTO.ddd(),
                dadosCriacaoTelefoneDTO.numero(),
                clienteEntity
        );

    }

    /**
     * Método para converter os dados retornados para a API, para um DadosCriacaoTelefoneDTO.
     *
     * @param telefoneEntity Objeto TelefoneEntity a ser convertido.
     * @return Objeto DadosCriacaoTelefoneDTO resultante da conversão.
     */
    public DadosCriacaoTelefoneDTO converterParaDTO(TelefoneEntity telefoneEntity) {

        return new DadosCriacaoTelefoneDTO(
                telefoneEntity.getDdi(),
                telefoneEntity.getDdd(),
                telefoneEntity.getNumero()
        );

    }

}
