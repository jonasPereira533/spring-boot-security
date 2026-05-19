package br.com.jonas.Spring_boot_essentials.controller;


import br.com.jonas.Spring_boot_essentials.dto.AvaliacaoFisicaDto;
import br.com.jonas.Spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import br.com.jonas.Spring_boot_essentials.exception.NotFound;
import br.com.jonas.Spring_boot_essentials.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacoesFisicasController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFound, BadRequestException {
        avaliacaoFisicaService.criarAvaliacoesFisicas(avaliacaoFisicaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
        return avaliacaoFisicaService.getAllAvaliacoes();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacoesFisicasProjection> getAllAvaliacoes(@PathVariable Integer page,
                                                              @PathVariable Integer size) throws NotFound, BadRequestException {
        return avaliacaoFisicaService.getAllAvaliacoesPageable(page,size);
    }
}
