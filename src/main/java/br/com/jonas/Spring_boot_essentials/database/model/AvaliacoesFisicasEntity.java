package br.com.jonas.Spring_boot_essentials.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import br.com.jonas.Spring_boot_essentials.database.model.AlunosAntity;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacoes_fisicas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties (("hibernateLazyInitializer"))

public class AvaliacoesFisicasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "peso", nullable = false) // posso ou não colocar a notação
    private BigDecimal peso; // é interessante usar quando o nome da tabela no banco de dados é diferente


    @Column(nullable = false)
    private BigDecimal altura;
    // exemplo sem
    // o JPA vai indentificar que o nome da tebla é altura


    @Column(name = "porcentagem_gordura_corporal", nullable = false) // nesse caso utilizei
    private BigDecimal porcentagemGorduraCorporal; // pois no banco usamos snake case

}
