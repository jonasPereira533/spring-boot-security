package br.com.jonas.Spring_boot_essentials.controller;


import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import br.com.jonas.Spring_boot_essentials.dto.ExerciciosDto;
import br.com.jonas.Spring_boot_essentials.service.ExecicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
@Validated
public class ExerciciosController {

    private final ExecicioService execicioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosAntity> findAll(){
        return execicioService.findAllExercicios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercicio(@Valid @RequestBody ExerciciosDto exerciciosDto){
        execicioService.save(exerciciosDto);
    }

    @GetMapping("/grupos/{grupoMuscular}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosAntity> getExerciciosByGrupoMuscular(@PathVariable String grupoMuscular){
        return execicioService.getExercicioByGrupoMuscular(grupoMuscular);
    }
}
