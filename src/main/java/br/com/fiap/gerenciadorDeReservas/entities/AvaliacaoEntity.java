package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Classe para representar as avaliações de cliente
 * para os restaurantes.
 */
@Entity
@Data
@Table(name = "tb_avaliacao")
public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;
    private Double nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    // **************
    // Construtores
    // **************

    public AvaliacaoEntity() {
    }

    public AvaliacaoEntity(String autor, Double nota, String comentario, LocalDateTime dataAvaliacao,
                           RestauranteEntity restauranteEntity) {

        this.autor = autor;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.restauranteEntity = restauranteEntity;
    }

}
