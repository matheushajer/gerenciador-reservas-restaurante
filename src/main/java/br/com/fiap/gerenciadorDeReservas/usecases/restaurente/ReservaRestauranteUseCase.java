package br.com.fiap.gerenciadorDeReservas.usecases.restaurente;

import br.com.fiap.gerenciadorDeReservas.entities.ReservaEntity;
import br.com.fiap.gerenciadorDeReservas.entities.enuns.statusEnum;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosFecharReservaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosListaReservaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.records.restaurante.DadosReservaRestauranteDTO;
import br.com.fiap.gerenciadorDeReservas.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.ReservaRepository;
import br.com.fiap.gerenciadorDeReservas.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe para representar o caso de uso da criação de um restaurante
 */
@Service
@Transactional(readOnly = true)
public class ReservaRestauranteUseCase {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ReservaRepository reservaRepository;

    /**
     * Método para efetuar a criação de uma entity RestauranteEntity e gravar no banco.
     *
     * @param dadosReservaRestauranteDTO Objeto DadosCriacaoRestauranteDTO com os dados de criação do RestauranteEntity.
     * @return Objeto DadosCriacaoRestauranteDTO com os dados gravados.
     */
    public ResponseEntity<?> reservaRestaurante(DadosReservaRestauranteDTO dadosReservaRestauranteDTO) {
        try {
            var existeRestaurante = restauranteRepository.findById(dadosReservaRestauranteDTO.idRestaurante());
            if (existeRestaurante.isEmpty()) {
                throw new RuntimeException("Restaurante não existe.");
            }

            var cliente = clienteRepository.findByCpf(dadosReservaRestauranteDTO.cpf());
            if (cliente == null) {
                throw new RuntimeException("Cliente não existe.");
            }

            LocalDateTime dataReserva = dadosReservaRestauranteDTO.dtareserva();
            LocalDateTime dataInicio = dataReserva.withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime dataFim = dataInicio.plusDays(1);

            int reservasDoDia = reservaRepository.countReservasByRestauranteAndData(existeRestaurante.get().getId(), dataInicio, dataFim); // Correção aqui
            int capacidadeRestaurante = existeRestaurante.get().getCapacidade();
            if (reservasDoDia >= capacidadeRestaurante) {
                throw new RuntimeException("Capacidade do restaurante cheia para esse dia.");
            }

            var reservas = reservaRepository.findByidRestaurante(existeRestaurante.get().getId());
            for (ReservaEntity reserva : reservas) {
                LocalDateTime horaReservaExistente = reserva.getReservadthora();
                LocalDateTime horaNovaReserva = dadosReservaRestauranteDTO.dtareserva();
                long diferencaMinutos = ChronoUnit.MINUTES.between(horaReservaExistente, horaNovaReserva);
                if (Math.abs(diferencaMinutos) < 15 && reserva.getStatusEnum() == statusEnum.ABERTA) {
                    throw new RuntimeException("Já existe uma reserva para este dia e horário para o restaurante selecionado.\nData e Hora: " + reserva.getReservadthora() + "\nStatus:" + reserva.getStatusEnum());
                }
                if (reserva.getCliente().getId().equals(cliente.getId()) && reserva.getReservadthora().toLocalDate().isEqual(dataReserva.toLocalDate()) && reserva.getStatusEnum() == statusEnum.ABERTA) {
                    throw new RuntimeException("Já existe uma reserva para este cliente no mesmo restaurante no mesmo dia.\nData e Hora: " + reserva.getReservadthora() + "\nStatus:" + reserva.getStatusEnum());
                }
            }

            ReservaEntity reservaEntity = new ReservaEntity();
            reservaEntity.setIdrestaurante(existeRestaurante.get().getId());
            reservaEntity.setReservadthora(dadosReservaRestauranteDTO.dtareserva());
            reservaEntity.setCliente(cliente);
            reservaEntity.setTelefone(cliente.getTelefone());
            reservaEntity.setStatusEnum(statusEnum.ABERTA);
            reservaRepository.save(reservaEntity);

            return ResponseEntity.ok("Reservada criada com sucesso para o dia: " + dadosReservaRestauranteDTO.dtareserva());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> fecharReservaRestaurante(DadosFecharReservaRestauranteDTO dadosFecharReservaRestauranteDTO) {

        try {
            var existeRestaurante = restauranteRepository.findById(dadosFecharReservaRestauranteDTO.idRestaurante());
            if (existeRestaurante.isEmpty()) {
                throw new RuntimeException("Restaurante não existe.");
            }

            var cliente = clienteRepository.findByCpf(dadosFecharReservaRestauranteDTO.cpf());
            if (cliente == null) {
                throw new RuntimeException("Cliente não existe.");
            }

            // Buscar a reserva específica para o cliente, restaurante, data e status "ABERTA"
            List<ReservaEntity> reservas = reservaRepository.findByClienteAndIdrestauranteAndReservadthoraAndStatusEnum(cliente, existeRestaurante.get().getId(), dadosFecharReservaRestauranteDTO.dtareserva(), statusEnum.ABERTA);
            if (reservas.isEmpty()) {
                throw new RuntimeException("Reserva não encontrada.");
            }

            // Fechar as reservas encontradas
            for (ReservaEntity reserva : reservas) {
                reserva.setStatusEnum(statusEnum.CANCELADA);
                reservaRepository.save(reserva);
            }

            return ResponseEntity.ok("Reservas canceladas com sucesso.");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    public ResponseEntity<?> listaReservaRestaurante(DadosListaReservaRestauranteDTO listaReservaRestauranteDTO) {
        List<ReservaEntity> reservas = new ArrayList<>();

        try {
            if (listaReservaRestauranteDTO.cpf() == null && listaReservaRestauranteDTO.idRestaurante() != null) {
                var existeRestaurante = restauranteRepository.findById(listaReservaRestauranteDTO.idRestaurante());
                if (existeRestaurante.isEmpty()) {
                    throw new RuntimeException("Restaurante não existe.");
                }
                if (listaReservaRestauranteDTO.dtareserva() != null) {
                    reservas = reservaRepository.findByIdrestauranteAndReservadthoraAndStatusEnum(existeRestaurante.get().getId(), listaReservaRestauranteDTO.dtareserva(), statusEnum.getStatusEnumFromString(listaReservaRestauranteDTO.status()));
                    if (reservas.isEmpty()) {
                        throw new RuntimeException("Reserva não encontrada.");
                    }
                }
                if (listaReservaRestauranteDTO.dtareserva() == null) {
                    reservas = reservaRepository.findByIdrestauranteAndStatusEnum(existeRestaurante.get().getId(), statusEnum.getStatusEnumFromString(listaReservaRestauranteDTO.status()));
                    if (reservas.isEmpty()) {
                        throw new RuntimeException("Reserva não encontrada.");
                    }
                }
            }
            if (listaReservaRestauranteDTO.cpf() != null && listaReservaRestauranteDTO.idRestaurante() == null) {
                var cliente = clienteRepository.findByCpf(listaReservaRestauranteDTO.cpf());
                if (cliente == null) {
                    throw new RuntimeException("Cliente não existe.");
                }
                if (listaReservaRestauranteDTO.dtareserva() != null) {
                    reservas = reservaRepository.findByClienteAndReservadthoraAndStatusEnum(cliente, listaReservaRestauranteDTO.dtareserva(), statusEnum.getStatusEnumFromString(listaReservaRestauranteDTO.status()));
                    if (reservas.isEmpty()) {
                        throw new RuntimeException("Reserva não encontrada.");
                    }
                }
                if (listaReservaRestauranteDTO.dtareserva() == null) {
                    reservas = reservaRepository.findByClienteAndStatusEnum(cliente, statusEnum.getStatusEnumFromString(listaReservaRestauranteDTO.status()));
                    if (reservas.isEmpty()) {
                        throw new RuntimeException("Reserva não encontrada.");
                    }
                }
            }
            if (listaReservaRestauranteDTO.cpf() != null && listaReservaRestauranteDTO.idRestaurante() != null) {
                var cliente = clienteRepository.findByCpf(listaReservaRestauranteDTO.cpf());
                if (cliente == null) {
                    throw new RuntimeException("Cliente não existe.");
                }
                var existeRestaurante = restauranteRepository.findById(listaReservaRestauranteDTO.idRestaurante());
                if (existeRestaurante.isEmpty()) {
                    throw new RuntimeException("Restaurante não existe.");
                }

                if (listaReservaRestauranteDTO.dtareserva() != null) {
                    reservas = reservaRepository.findByClienteAndReservadthoraAndStatusEnum(cliente, listaReservaRestauranteDTO.dtareserva(), statusEnum.getStatusEnumFromString(listaReservaRestauranteDTO.status()));
                    if (reservas.isEmpty()) {
                        throw new RuntimeException("Reserva não encontrada.");
                    }
                }
                if (listaReservaRestauranteDTO.dtareserva() == null) {
                    reservas = reservaRepository.findByClienteAndStatusEnum(cliente, statusEnum.getStatusEnumFromString(listaReservaRestauranteDTO.status()));
                    if (reservas.isEmpty()) {
                        throw new RuntimeException("Reserva não encontrada.");
                    }
                }
            }


            if (listaReservaRestauranteDTO.cpf() == null && listaReservaRestauranteDTO.idRestaurante() == null) {
                throw new RuntimeException("Preencha o restaurante ou cliente para ter a lista.");
            }

            return ResponseEntity.ok(reservas);
        } catch (RuntimeException ex) {
            // Se ocorrer uma exceção, retornar uma resposta de erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }
}