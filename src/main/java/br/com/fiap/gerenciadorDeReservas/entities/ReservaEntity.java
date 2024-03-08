package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.statusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe para representar a Entidade Restaurante
 */
@Entity
@Getter
@Setter
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
    @JoinColumn(name = "telefone")
    private TelefoneEntity telefone;
    @ManyToOne
    @JoinColumn(name = "cliente")
    private ClienteEntity cliente;

}
