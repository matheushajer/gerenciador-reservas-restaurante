package br.com.fiap.gerenciadorDeReservas.usecases.reserva;

import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FecharReservaUseCaseTest {
    @Mock
    ReservaRepository reservaRepository;
    @InjectMocks
    FecharReservaUseCase fecharReservaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFecharReservaIdInvalido() {

        when(reservaRepository.findById(any()))
                .thenReturn(Optional.empty()); // Retorna um Optional vazio


        assertThrows(EntityNotFoundException.class, () -> {
            fecharReservaUseCase.fecharReserva(1L);
        });


        verify(reservaRepository, never()).atualizarStatusReserva(anyLong(), any());
    }

    @Test
    void testFecharReserva_IdValido() {

        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setId(1L);
        reservaEntity.setStatusReservaEnum(StatusReservaEnum.ABERTA);


        when(reservaRepository.findById(1L))
                .thenReturn(Optional.of(reservaEntity));

        fecharReservaUseCase.fecharReserva(1L);

        assertEquals(StatusReservaEnum.CANCELADA, reservaEntity.getStatusReservaEnum());

        verify(reservaRepository).atualizarStatusReserva(1L, StatusReservaEnum.CANCELADA);
    }


}
