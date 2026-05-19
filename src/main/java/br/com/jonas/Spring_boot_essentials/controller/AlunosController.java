package br.com.jonas.Spring_boot_essentials.controller;


import br.com.jonas.Spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import br.com.jonas.Spring_boot_essentials.dto.AlunoDto;
import br.com.jonas.Spring_boot_essentials.dto.ExerciciosDto;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import br.com.jonas.Spring_boot_essentials.exception.NotFound;
import br.com.jonas.Spring_boot_essentials.service.AlunosService;
import br.com.jonas.Spring_boot_essentials.service.ExecicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunosController {

    private final AlunosService alunosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunoDto alunoDto) throws BadRequestException {
        alunosService.criarAluno(alunoDto);
    }


    @PreAuthorize("#alunoId == authentication.principal.id or hasRole('ADMIN')")  // tente ver de outro aluno isso vai gerar um erro do tipo accessDenaid
    @GetMapping("/{alunoId}/avaliacao")
    public AvaliacoesFisicasEntity getAvaliacao(@PathVariable Integer alunoId) throws NotFound {
        return alunosService.getAlunoAvaliacao(alunoId);
    }

    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAluno(@PathVariable Integer alunoId) throws NotFound {
        alunosService.deletarAluno(alunoId);
    }
}
