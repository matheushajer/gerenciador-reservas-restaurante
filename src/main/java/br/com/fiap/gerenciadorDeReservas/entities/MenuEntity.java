package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_menu")
public class MenuEntity {

    private Long id;
    private String nomePrato;
    private Double preco;
    private String fotoUrl;
    private List<String> ingredientes;

    @OneToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    // Construtores

    public MenuEntity() {
    }

    public MenuEntity(Long id, String nomePrato, Double preco, String fotoUrl, List<String> ingredientes, RestauranteEntity restauranteEntity) {
        this.id = id;
        this.nomePrato = nomePrato;
        this.preco = preco;
        this.fotoUrl = fotoUrl;
        this.ingredientes = ingredientes;
        this.restauranteEntity = restauranteEntity;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNomePrato() {
        return nomePrato;
    }

    public Double getPreco() {
        return preco;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public RestauranteEntity getRestauranteEntity() {
        return restauranteEntity;
    }
}
