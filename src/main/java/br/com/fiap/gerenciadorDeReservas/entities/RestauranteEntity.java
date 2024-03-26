package br.com.fiap.gerenciadorDeReservas.entities;

import br.com.fiap.gerenciadorDeReservas.entities.enuns.TipoCulinariaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para representar a Entidade Restaurante
 */
@Entity
@Data
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
    private List<LocalDateTime> diasDeOperacao = new ArrayList<>();

    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AvaliacaoEntity> avaliacoes;

    @OneToMany(mappedBy = "restauranteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ReservaEntity> reservaEntity;

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

    /**
     * Cria uma lista de data e hora, para os proximos três meses
     * respeitando o horário de abertura e fechamento do restaurante.
     */
    public void setDiasDeOperacao() {

        LocalDateTime dataAtual = LocalDateTime.now().with(horarioDeAbertura);
        LocalDateTime dataFinal = dataAtual.plusMonths(3).withHour(horarioDeFechamento.getHour());

        while (dataAtual.isBefore(dataFinal)) {

            diasDeOperacao.add(dataAtual);

            dataAtual = dataAtual.plusHours(1);

            if (dataAtual.toLocalTime().equals(LocalTime.MIDNIGHT)) {
                dataAtual = dataAtual.with(horarioDeAbertura);
            }

        }

    }


}