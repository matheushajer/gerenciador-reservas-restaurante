package br.com.fiap.gerenciadorDeReservas.usecases.restaurente;

import br.com.fiap.gerenciadorDeReservas.adapters.restaurante.RestauranteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosCriacaoRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
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

    /**
     * Método para efetuar a criação de uma entity RestauranteEntity e gravar no banco.
     *
     * @param dadosCriacaoClienteDTO Objeto DadosCriacaoRestauranteDTO com os dados de criação do RestauranteEntity.
     * @return Objeto DadosCriacaoRestauranteDTO com os dados gravados.
     */
    public DadosCriacaoClienteDTO criarCliente(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setEndereco(dadosCriacaoClienteDTO.cliente().getEndereco());
        clienteEntity.setNome(dadosCriacaoClienteDTO.cliente().getNome());
        clienteEntity.setTelefone(dadosCriacaoClienteDTO.cliente().getTelefone());
        clienteEntity.setCpf(dadosCriacaoClienteDTO.cliente().getCpf());
        ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);
        dadosCriacaoClienteDTO.cliente().setId(clienteSalvo.getId());
        return dadosCriacaoClienteDTO;

    }

}
