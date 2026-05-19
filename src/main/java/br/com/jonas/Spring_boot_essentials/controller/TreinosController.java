package br.com.jonas.Spring_boot_essentials.controller;


import br.com.jonas.Spring_boot_essentials.dto.TreinoDto;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import br.com.jonas.Spring_boot_essentials.exception.NotFound;
import br.com.jonas.Spring_boot_essentials.service.TreinoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/treinos")
@AllArgsConstructor


public class TreinosController {

    private final TreinoService treinoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTreino (@Valid @RequestBody TreinoDto treinoDto) throws NotFound, BadRequestException {
        treinoService.criarTreino(treinoDto);
    }

}
