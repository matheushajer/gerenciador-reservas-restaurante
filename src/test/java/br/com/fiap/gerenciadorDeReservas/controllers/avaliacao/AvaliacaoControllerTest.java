package br.com.fiap.gerenciadorDeReservas.controllers.avaliacao;

import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.usecases.avaliacao.CriarAvaliacaoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AvaliacaoControllerTest {
    @Mock
    CriarAvaliacaoUseCase criarAvaliacaoUseCase;
    @InjectMocks
    AvaliacaoController avaliacaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarAvaliacao() {
        when(criarAvaliacaoUseCase.criarAvaliacao(any()))
                .thenReturn(new DadosRetornoCriacaoAvaliacaoDTO("autor", (double) 0, "comentario"));

        ResponseEntity<DadosRetornoCriacaoAvaliacaoDTO> result =
                avaliacaoController.criarAvaliacao(
                        new DadosCriacaoAvaliacaoDTO(1L, 1L, (double) 0, "comentario")
                );

        Assertions.assertEquals(
                new ResponseEntity<DadosRetornoCriacaoAvaliacaoDTO>(
                        new DadosRetornoCriacaoAvaliacaoDTO("autor", (double) 0, "comentario"),
                        null,
                        200
                ),
                result
        );
    }

}
