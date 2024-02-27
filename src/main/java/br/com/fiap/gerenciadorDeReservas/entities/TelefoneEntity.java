package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_telefone")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ddi;
    private int ddd;
    private int telefone;
    private boolean isTelefonePrincipal;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    // Construtores

    public TelefoneEntity() {
    }

    public TelefoneEntity(Long id, int ddi, int ddd, int telefone, boolean isTelefonePrincipal,
                          RestauranteEntity restauranteEntity) {
        this.id = id;
        this.ddi = ddi;
        this.ddd = ddd;
        this.telefone = telefone;
        this.isTelefonePrincipal = isTelefonePrincipal;
        this.restauranteEntity = restauranteEntity;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public int getDdi() {
        return ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public int getTelefone() {
        return telefone;
    }

    public boolean isTelefonePrincipal() {
        return isTelefonePrincipal;
    }

    public RestauranteEntity getRestauranteEntity() {
        return restauranteEntity;
    }
}
