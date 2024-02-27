package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.FormasDePagamentoEnum;
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
    private String descricao;
    @OneToOne(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private EnderecoEntity enderecoEntity;
    private String cnpj;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TelefoneEntity> telefones;
    private List<FormasDePagamentoEnum> formasDePagamento;
    private TipoCulinariaEnum tipoCulinaria;
    private Integer capacidade;
    private LocalDate horarioDeFuncionamento;
    private LocalDate horarioDeFechamento;
    private List<LocalDate> diasDeOperacao;
    private LocalDate dataInoperante;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AvaliacaoEntity> avaliacoes;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItensMenuEntity> menu;
    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MesaEntity> mesas;
    private Double toleranciaDeAtraso;
    @OneToOne(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private LoginEntity login;


    // Construtores

    public RestauranteEntity() {
    }

    public RestauranteEntity(String nome, String descricao, EnderecoEntity enderecoEntity, String cnpj,
                             LoginEntity login) {
        this.nome = nome;
        this.descricao = descricao;
        this.enderecoEntity = enderecoEntity;
        this.cnpj = cnpj;
        this.login = login;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public EnderecoEntity getEndereco() {
        return enderecoEntity;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<TelefoneEntity> getTelefones() {
        return telefones;
    }

    public List<FormasDePagamentoEnum> getFormasDePagamento() {
        return formasDePagamento;
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

    public List<ItensMenuEntity> getMenu() {
        return menu;
    }

    public List<MesaEntity> getMesas() {
        return mesas;
    }

    public Double getToleranciaDeAtraso() {
        return toleranciaDeAtraso;
    }

    public LoginEntity getLogin() {
        return login;
    }
}
