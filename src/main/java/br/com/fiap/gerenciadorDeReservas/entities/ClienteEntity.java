package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Classe para representar a Entidade Restaurante
 */
@Entity
@Data
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    @OneToOne(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TelefoneEntity telefone;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ReservaEntity> reservaEntity;

    // **************
    // Construtores
    // **************

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

}
