package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Classe para representar a Entidade Telefone
 */
@Getter
@Entity
@Table(name = "tb_telefone")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ddi;
    private int ddd;
    private int numero;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    // **************
    // Construtores
    // **************

    public TelefoneEntity() {
    }

    public TelefoneEntity(int ddi, int ddd, int telefone, ClienteEntity clienteEntity) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = telefone;
        this.clienteEntity = clienteEntity;
    }

}
