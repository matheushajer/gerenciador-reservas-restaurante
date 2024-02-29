package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_restaurante")
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToOne(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private EnderecoEntity enderecoEntity;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TelefoneEntity> telefones;
    private TipoCulinariaEnum tipoCulinaria;
    private Integer capacidade;
    private LocalDate horarioDeFuncionamento;
    private LocalDate horarioDeFechamento;
    private List<LocalDate> diasDeOperacao;
    private LocalDate dataInoperante;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AvaliacaoEntity> avaliacoes;

    // **************
    // Construtores
    // **************

    public RestauranteEntity() {
    }

    public RestauranteEntity(String nome, EnderecoEntity enderecoEntity, TipoCulinariaEnum tipoCulinaria,
                             LocalDate horarioDeFuncionamento, LocalDate horarioDeFechamento) {
        this.nome = nome;
        this.enderecoEntity = enderecoEntity;
        this.tipoCulinaria = tipoCulinaria;
        this.horarioDeFuncionamento = horarioDeFuncionamento;
        this.horarioDeFechamento = horarioDeFechamento;

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

    public List<TelefoneEntity> getTelefones() {
        return telefones;
    }

    public TipoCulinariaEnum getTipoCulinaria() {
        return tipoCulinaria;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public LocalDate getHorarioDeFuncionamento() {
        return horarioDeFuncionamento;
    }

    public LocalDate getHorarioDeFechamento() {
        return horarioDeFechamento;
    }

    public List<LocalDate> getDiasDeOperacao() {
        return diasDeOperacao;
    }

    public LocalDate getDataInoperante() {
        return dataInoperante;
    }

    public List<AvaliacaoEntity> getAvaliacoes() {
        return avaliacoes;
    }

}
