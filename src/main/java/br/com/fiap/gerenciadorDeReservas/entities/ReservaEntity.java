package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.statusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe para representar a Entidade Restaurante
 */
@Entity
@Data
@Table(name = "tb_reserva")
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idrestaurante;
    private Long capacidade;
    private LocalDateTime reservadthora;
    private statusEnum statusEnum;

    @ManyToOne
    @JoinColumn(name = "telefone_id")
    private TelefoneEntity telefone;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

}
