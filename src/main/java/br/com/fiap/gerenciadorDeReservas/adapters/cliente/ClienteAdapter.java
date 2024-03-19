package br.com.fiap.gerenciadorDeReservas.adapters.cliente;

import br.com.fiap.gerenciadorDeReservas.adapters.telefone.TelefoneAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados.
 */
@Service
public class ClienteAdapter {

    @Autowired
    TelefoneAdapter telefoneAdapter;

    /**
     * Método para converter os dados vindo da API para um ClienteEntity.
     *
     * @param dadosCriacaoClienteDTO Objeto DadosCriacaoClienteDTO a ser convertido.
     * @return Objeto ClienteEntity resultante da conversão.
     */
    public ClienteEntity converterParaEntity(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        ClienteEntity clienteEntity = new ClienteEntity(
                dadosCriacaoClienteDTO.nome(),
                dadosCriacaoClienteDTO.cpf(),
                dadosCriacaoClienteDTO.email()
        );

        clienteEntity.setTelefoneEntity(telefoneAdapter.converterParaEntity(
                dadosCriacaoClienteDTO.dadosCriacaoTelefoneDTO(), clienteEntity));

        return clienteEntity;

    }

    /**
     * Método para converter os dados vindo da API para um ClienteEntity.
     *
     * @param clienteEntity Objeto ClienteEntity a ser convertido.
     * @return Objeto DadosCriacaoClienteDTO resultante da conversão.
     */
    public DadosCriacaoClienteDTO converterParaDTO(ClienteEntity clienteEntity) {

        return new DadosCriacaoClienteDTO(
                clienteEntity.getNome(),
                clienteEntity.getCpf(),
                clienteEntity.getEmail(),
                telefoneAdapter.converterParaDTO(clienteEntity.getTelefoneEntity())
        );

    }


}
