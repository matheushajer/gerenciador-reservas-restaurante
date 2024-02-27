package br.com.fiap.gerenciadorDeReservas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_login")
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String senha;
    private String tipoUsuario;

    @OneToOne
    @JoinColumn(name = "restaurante_id")
    private RestauranteEntity restauranteEntity;

    // Construtores

    public LoginEntity() {
    }

    public LoginEntity(Long id, String usuario, String senha, String tipoUsuario) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public RestauranteEntity getRestauranteEntity() {
        return restauranteEntity;
    }

    public void setRestauranteEntity(RestauranteEntity restauranteEntity) {
        this.restauranteEntity = restauranteEntity;
    }
}
