package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Classe para representar a Entidade Restaurante
 */
@Entity
@Getter
@Setter
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long cpf;
    @OneToOne(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @ManyToOne
    @JoinColumn(name = "telefone_id")
    private TelefoneEntity telefone;

}
