package br.com.fiap.gerenciadorDeReservas.controllers.cliente;

import br.com.fiap.gerenciadorDeReservas.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDeReservas.records.telefone.DadosCriacaoTelefoneDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.cliente.CriarClienteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class ClienteControllerTest {
    @Mock
    CriarClienteUseCase criarClienteUseCase;
    @InjectMocks
    ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarRestaurante() {
        when(criarClienteUseCase.criarCliente(any()))
                .thenReturn(new DadosCriacaoClienteDTO(
                        "nome", "cpf", "email", new DadosCriacaoTelefoneDTO(0, 0, 0)
                ));

        ResponseEntity<DadosCriacaoClienteDTO> result =
                clienteController.criarRestaurante(
                        new DadosCriacaoClienteDTO(
                                "nome", "cpf", "email", new DadosCriacaoTelefoneDTO(0, 0, 0)
                        )
                );

        Assertions.assertEquals(
                new ResponseEntity<DadosCriacaoClienteDTO>(
                        new DadosCriacaoClienteDTO(
                                "nome", "cpf", "email", new DadosCriacaoTelefoneDTO(0, 0, 0)
                        ),
                        null,
                        200
                ),
                result
        );
    }

}
