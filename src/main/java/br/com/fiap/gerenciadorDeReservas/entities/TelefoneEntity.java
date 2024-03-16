package br.com.fiap.gerenciadorDeReservas.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

/**
 * Classe para representar a Entidade Telefone
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_telefone")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ddi;
    private int ddd;
    private int numero;

    @OneToOne
    @JoinColumn(name = "restaurante_id")
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
    }

    // *****************
    //  Getter e Setter
    // *****************

    public Long getId() {
        return id;
    }

    public int getDdi() {
        return ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public int getNumero() {
        return numero;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

}
