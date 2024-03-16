package br.com.fiap.gerenciadorDeReservas.usecases.cliente;

import br.com.fiap.gerenciadorDeReservas.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para representar o caso de uso da criação de um restaurante
 */
@Service
@Transactional(readOnly = true)
public class CriarClienteUseCase {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteAdapter clienteAdapter;

    /**
     * Método para efetuar a criação de uma entity ClienteEntity e gravar no banco.
     *
     * @param dadosCriacaoClienteDTO Objeto DadosCriacaoClienteDTO com os dados de criação do ClienteEntity.
     * @return Objeto DadosCriacaoClienteDTO com os dados gravados.
     */
    public DadosCriacaoClienteDTO criarCliente(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        ClienteEntity clienteEntity = clienteAdapter.converterParaEntity(dadosCriacaoClienteDTO);

        clienteRepository.save(clienteEntity);

        return clienteAdapter.converterParaDTO(clienteEntity);

    }

}
