package br.com.fiap.gerenciadorDeReservas.adapters.reserva;

import br.com.fiap.gerenciadorDeReservas.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDeReservas.entities.ClienteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.RestauranteEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosConsultaReservasDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.records.reserva.DadosRetornoCriacaoReservaDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import br.com.fiap.gerenciadorDeReservas.usecases.reserva.util.ValidadorDeReservas;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados
 */
@Service
public class ReservaAdapter {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    EnderecoAdapter enderecoAdapter;
    @Autowired
    ValidadorDeReservas agendamentoValidoUseCase;

    /**
     * Método para efetuar a conversão dos dados da API para criação  de uma ReservaEntity.
     *
     * @param dadosCriacaoReservaDTO Objeto com os dados para conversão.
     * @return ReservaEntity Objeto com os dados tratodos.
     * @throws IllegalAccessException Exception lançada, em caso de horário não disponivel.
     */
    public ReservaEntity converterParaEntity(DadosCriacaoReservaDTO dadosCriacaoReservaDTO) throws IllegalAccessException {

        StatusReservaEnum statusReservaEnum = StatusReservaEnum.ABERTA;

        ClienteEntity clienteEntity = clienteRepository.findById(dadosCriacaoReservaDTO.cliente_id()).orElseThrow(
                () -> new EntityNotFoundException(("O cliente_id fornecido é inválido")));


        RestauranteEntity restauranteEntity = restauranteRepository.findById(dadosCriacaoReservaDTO.restaurante_id())
                .orElseThrow(() -> new EntityNotFoundException(("O restaurante_id fornecido é inválido")));

        ReservaEntity reservaEntity = new ReservaEntity(
                statusReservaEnum,
                clienteEntity,
                restauranteEntity
        );

        if (agendamentoValidoUseCase.isDataAgendamentoValido(dadosCriacaoReservaDTO.dataReserva(), restauranteEntity)) {
            reservaEntity.setDataReserva(dadosCriacaoReservaDTO.dataReserva());

        } else {
            throw new IllegalAccessException("Data indisponivel para reserva");
        }

        return reservaEntity;

    }

    /**
     * Método para converter uma ReservaEntity para um objeto DadosRetornoCriacaoReservaDTO.
     *
     * @param reservaEntity Objeto com os dados a serem convertidos.
     * @return DadosRetornoCriacaoReservaDTO Objeto com os dados tratados.
     */
    public DadosRetornoCriacaoReservaDTO converterParaDadosRetornoDTO(ReservaEntity reservaEntity) {

        return new DadosRetornoCriacaoReservaDTO(
                reservaEntity.getRestauranteEntity().getNome(),
                enderecoAdapter.converterParaDTO(reservaEntity.getRestauranteEntity().getEnderecoEntity()),
                reservaEntity.getDataReserva()
        );

    }

    /**
     * Método para converter os dados de uma ReservaEntity para um objeto
     * DadosConsultaReservasDTO.
     *
     * @param reservaEntity Objeto com os dados a serem convertidos.
     * @return DadosConsultaReservasDTO Objeto com os dados tratados para exibição.
     */
    public List<DadosConsultaReservasDTO> converterParaDadosConsultaPorRestaurante(List<ReservaEntity> reservaEntity) {

        List<DadosConsultaReservasDTO> dadosConsultaReservas = new ArrayList<>();

        reservaEntity.forEach(reserva -> {
            DadosConsultaReservasDTO dadosConsulta = new DadosConsultaReservasDTO(
                    reserva.getDataReserva(),
                    reserva.getStatusReservaEnum()
            );
            dadosConsultaReservas.add(dadosConsulta);
        });

        return dadosConsultaReservas;

    }


}
