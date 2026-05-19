package br.com.jonas.Spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercicios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ExerciciosAntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String grupoMuscular;

}
