package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "tb_avaliacao")
public class AvaliacaoEntity {

    private Long id;
    private String autor;
    private Double nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    // Construtores

    public AvaliacaoEntity() {
    }

    public AvaliacaoEntity(Long id, String autor, Double nota, String comentario, LocalDate dataAvaliacao,
                           RestauranteEntity restauranteEntity) {
        this.id = id;
        this.autor = autor;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.restauranteEntity = restauranteEntity;
    }

    // Getter e Setters

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public Double getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public RestauranteEntity getRestauranteEntity() {
        return restauranteEntity;
    }
}
