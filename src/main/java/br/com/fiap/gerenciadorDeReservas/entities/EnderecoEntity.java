package br.com.fiap.gerenciadorDeReservas.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Classe para representar a Entidade Endereco
 */
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @OneToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    // **************
    // Construtores
    // **************

    public EnderecoEntity() {
    }

    public EnderecoEntity(
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            RestauranteEntity restauranteEntity) {

        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.restauranteEntity = restauranteEntity;
    }

}
