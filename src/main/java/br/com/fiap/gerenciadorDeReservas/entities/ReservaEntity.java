package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.StatusReservaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe para representar a Entidade Reserva.
 */
@Entity
@Data
@Table(name = "tb_reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataReserva;

    @Enumerated(EnumType.STRING)
    private StatusReservaEnum StatusReservaEnum;

    @ManyToOne
    @JoinColumn(name = "telefone_id")
    private TelefoneEntity telefone;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

}
