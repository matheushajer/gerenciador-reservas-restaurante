package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoDeMesaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mesa")
public class MesaEntity {

    private Long id;
    private Integer numeroMesa;
    private String posicaoDaMesa;
    private boolean disponibilidadeDaMesa;
    private TipoDeMesaEnum tipoDeMesa;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    // Construtor

    public MesaEntity() {
    }

    public MesaEntity(Long id, Integer numeroMesa, String posicaoDaMesa, boolean disponibilidadeDaMesa,
                      TipoDeMesaEnum tipoDeMesa, RestauranteEntity restauranteEntity) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.posicaoDaMesa = posicaoDaMesa;
        this.disponibilidadeDaMesa = disponibilidadeDaMesa;
        this.tipoDeMesa = tipoDeMesa;
        this.restauranteEntity = restauranteEntity;
    }

    // Getter e Setter

    public Long getId() {
        return id;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public String getPosicaoDaMesa() {
        return posicaoDaMesa;
    }

    public boolean isDisponibilidadeDaMesa() {
        return disponibilidadeDaMesa;
    }

    public TipoDeMesaEnum getTipoDeMesa() {
        return tipoDeMesa;
    }

    public RestauranteEntity getRestauranteEntity() {
        return restauranteEntity;
    }
}
