package br.com.fiap.gerenciadorDeReservas.adapters.telefone;

import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDeReservas.records.telefone.DadosCriacaoTelefoneDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TelefoneAdapterTest {

    TelefoneAdapter telefoneAdapter = new TelefoneAdapter();

    @Test
    void testConverterParaEntity_DDI() {
        DadosCriacaoTelefoneDTO dadosCriacaoTelefoneDTO = new DadosCriacaoTelefoneDTO(1, 0, 0);
        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");

        TelefoneEntity result = telefoneAdapter.converterParaEntity(dadosCriacaoTelefoneDTO, clienteEntity);

        assertEquals(1, result.getDdi());
    }

    @Test
    void testConverterParaEntity_DDD() {
        DadosCriacaoTelefoneDTO dadosCriacaoTelefoneDTO = new DadosCriacaoTelefoneDTO(0, 2, 0);
        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");

        TelefoneEntity result = telefoneAdapter.converterParaEntity(dadosCriacaoTelefoneDTO, clienteEntity);

        assertEquals(2, result.getDdd());
    }

    @Test
    void testConverterParaEntity_Numero() {
        DadosCriacaoTelefoneDTO dadosCriacaoTelefoneDTO = new DadosCriacaoTelefoneDTO(0, 0, 3);
        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");

        TelefoneEntity result = telefoneAdapter.converterParaEntity(dadosCriacaoTelefoneDTO, clienteEntity);

        assertEquals(3, result.getNumero());
    }

    @Test
    void testConverterParaEntity_ClienteEntity() {

        DadosCriacaoTelefoneDTO dadosCriacaoTelefoneDTO = new DadosCriacaoTelefoneDTO(0, 0, 0);
        ClienteEntity clienteEntity = new ClienteEntity("nome", "cpf", "email");

        TelefoneEntity result = telefoneAdapter.converterParaEntity(dadosCriacaoTelefoneDTO, clienteEntity);

        assertEquals(clienteEntity, result.getClienteEntity());
    }

    @Test
    void testConverterParaDTO() {
        DadosCriacaoTelefoneDTO result = telefoneAdapter.converterParaDTO(
                new TelefoneEntity(0, 0, 0, null)
        );
        assertEquals(
                new DadosCriacaoTelefoneDTO(0, 0, 0),
                result
        );
    }

}

