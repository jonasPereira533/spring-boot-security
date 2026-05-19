package br.com.jonas.Spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class TreinoDto {

    @NotNull
    private Integer alunoId;

    @NotBlank
    private String nome;

    private List<Integer> exerciciosIds;
}
