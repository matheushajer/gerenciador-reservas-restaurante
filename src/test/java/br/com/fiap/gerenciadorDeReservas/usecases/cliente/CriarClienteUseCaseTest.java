package br.com.fiap.gerenciadorDeReservas.usecases.cliente;

import br.com.fiap.gerenciadorDeReservas.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.records.telefone.DadosCriacaoTelefoneDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class CriarClienteUseCaseTest {
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    ClienteAdapter clienteAdapter;
    @InjectMocks
    CriarClienteUseCase criarClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarCliente() {

        when(clienteRepository.save(any(ClienteEntity.class)))
                .thenReturn(new ClienteEntity("nome", "cpf", "email"));

        when(clienteAdapter.converterParaEntity(any()))
                .thenReturn(new ClienteEntity("nome", "cpf", "email"));

        when(clienteAdapter.converterParaDTO(any()))
                .thenReturn(new DadosCriacaoClienteDTO("nome", "cpf", "email",
                        new DadosCriacaoTelefoneDTO(0, 0, 0)));

        DadosCriacaoClienteDTO result = criarClienteUseCase
                .criarCliente(new DadosCriacaoClienteDTO("nome", "cpf", "email",
                        new DadosCriacaoTelefoneDTO(0, 0, 0)));

        Assertions.assertEquals(
                new DadosCriacaoClienteDTO("nome", "cpf", "email",
                        new DadosCriacaoTelefoneDTO(0, 0, 0)),
                result);
    }

}

