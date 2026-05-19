package br.com.jonas.Spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "treinos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class TreinosAntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String nome;

    @OneToOne
    @JoinColumn(name = "avaliacao_fisica_id")
    private AvaliacoesFisicasEntity avaliacaoFisica;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunosAntity aluno;

    @ManyToMany
    @JoinTable(
            name = "treinos_exercicios",
            joinColumns = @JoinColumn(name = "treino_id"),
            inverseJoinColumns = @JoinColumn(name = "exercicio_id")
    )
    private Set<ExerciciosAntity> execicios = new HashSet<>();

}
