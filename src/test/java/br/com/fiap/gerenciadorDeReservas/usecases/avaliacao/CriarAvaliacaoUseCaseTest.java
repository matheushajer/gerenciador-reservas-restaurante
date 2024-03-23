package br.com.fiap.gerenciadorDeReservas.usecases.avaliacao;

import br.com.fiap.gerenciadorDeReservas.adapters.avaliacao.AvaliacaoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.AvaliacaoEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.records.avaliacao.DadosRetornoCriacaoAvaliacaoDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.AvaliacaoRespository;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class CriarAvaliacaoUseCaseTest {
    @Mock
    AvaliacaoRespository avaliacaoRespository;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    RestauranteRepository restauranteRepository;
    @Mock
    AvaliacaoAdapter avaliacaoAdapter;
    @InjectMocks
    CriarAvaliacaoUseCase criarAvaliacaoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarAvaliacao() {
        ClienteEntity clienteEntity = new ClienteEntity();
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();

        DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO = new DadosCriacaoAvaliacaoDTO(1L, 1L, 0.0, "comentario");
        when(clienteRepository.findById(any())).thenReturn(java.util.Optional.of(clienteEntity));
        when(restauranteRepository.findById(any())).thenReturn(java.util.Optional.of(restauranteEntity));
        when(avaliacaoAdapter.converterParaEntity(any(), any(), any())).thenReturn(avaliacaoEntity);
        when(avaliacaoAdapter.converterParaDadosRetornoDTO(any())).thenReturn(new DadosRetornoCriacaoAvaliacaoDTO("autor", 0.0, "comentario"));

        DadosRetornoCriacaoAvaliacaoDTO result = criarAvaliacaoUseCase.criarAvaliacao(dadosCriacaoAvaliacaoDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("autor", result.autor());
        Assertions.assertEquals(0.0, result.nota());
        Assertions.assertEquals("comentario", result.comentario());
    }

    @Test
    void testCriarAvaliacao_ClienteInvalido() {
        DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO = new DadosCriacaoAvaliacaoDTO(1L, 1L, 0.0, "comentario");
        when(clienteRepository.findById(any())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            criarAvaliacaoUseCase.criarAvaliacao(dadosCriacaoAvaliacaoDTO);
        });
    }

    @Test
    void testCriarAvaliacao_RestauranteInvalido() {
        DadosCriacaoAvaliacaoDTO dadosCriacaoAvaliacaoDTO = new DadosCriacaoAvaliacaoDTO(1L, 1L, 0.0, "comentario");
        ClienteEntity clienteEntity = new ClienteEntity();
        when(clienteRepository.findById(any())).thenReturn(java.util.Optional.of(clienteEntity));
        when(restauranteRepository.findById(any())).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            criarAvaliacaoUseCase.criarAvaliacao(dadosCriacaoAvaliacaoDTO);
        });
    }

}
