package br.com.fiap.gerenciadorDeReservas.adapters.cliente;

import br.com.fiap.gerenciadorDeReservas.adapters.telefone.TelefoneAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.records.telefone.DadosCriacaoTelefoneDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ClienteAdapterTest {
    @Mock
    TelefoneAdapter telefoneAdapter;
    @InjectMocks
    ClienteAdapter clienteAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConverterParaEntity() {
        DadosCriacaoTelefoneDTO dadosTelefoneDTO = new DadosCriacaoTelefoneDTO(0, 0, 0);
        DadosCriacaoClienteDTO dadosClienteDTO = new DadosCriacaoClienteDTO("nome", "cpf", "email", dadosTelefoneDTO);

        when(telefoneAdapter.converterParaEntity(eq(dadosTelefoneDTO), any()))
                .thenReturn(new TelefoneEntity(0, 0, 0, null));

        ClienteEntity result = clienteAdapter.converterParaEntity(dadosClienteDTO);

        assertEquals("nome", result.getNome());
        assertEquals("cpf", result.getCpf());
        assertEquals("email", result.getEmail());
        assertNotNull(result.getTelefoneEntity());
    }

    @Test
    void testConverterParaDTO() {
        when(telefoneAdapter.converterParaDTO(any())).thenReturn(new DadosCriacaoTelefoneDTO(0, 0, 0));

        DadosCriacaoClienteDTO result = clienteAdapter.converterParaDTO(
                new ClienteEntity("nome", "cpf", "email"));

        assertEquals(new DadosCriacaoClienteDTO("nome", "cpf", "email",
                new DadosCriacaoTelefoneDTO(0, 0, 0)), result);
    }
}
