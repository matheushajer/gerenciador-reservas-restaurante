package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Classe para representar a Entidade Restaurante
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private EnderecoEntity enderecoEntity;
    @Enumerated(EnumType.STRING)
    private TipoCulinariaEnum tipoCulinaria;
    private Integer capacidade;
    private LocalTime horarioDeAbertura;
    private LocalTime horarioDeFechamento;
    private List<LocalDate> diasDeOperacao;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AvaliacaoEntity> avaliacoes;

    // **************
    // Construtores
    // **************

    public RestauranteEntity() {
    }

    public RestauranteEntity(String nome, TipoCulinariaEnum tipoCulinaria,
                             LocalTime horarioDeAbertura, LocalTime horarioDeFechamento, Integer capacidade) {
        this.nome = nome;
        this.tipoCulinaria = tipoCulinaria;
        this.horarioDeAbertura = horarioDeAbertura;
        this.horarioDeFechamento = horarioDeFechamento;
        this.capacidade = capacidade;

    }

    // *****************
    //  Getter e Setter
    // *****************

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public EnderecoEntity getEndereco() {
        return enderecoEntity;
    }

    public void setEnderecoEntity(EnderecoEntity enderecoEntity) {
        this.enderecoEntity = enderecoEntity;
    }

    public TipoCulinariaEnum getTipoCulinaria() {
        return tipoCulinaria;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public LocalTime getHorarioDeAbertura() {
        return horarioDeAbertura;
    }

    public LocalTime getHorarioDeFechamento() {
        return horarioDeFechamento;
    }

    public List<LocalDate> getDiasDeOperacao() {
        return diasDeOperacao;
    }

    public List<AvaliacaoEntity> getAvaliacoes() {
        return avaliacoes;
    }

}
