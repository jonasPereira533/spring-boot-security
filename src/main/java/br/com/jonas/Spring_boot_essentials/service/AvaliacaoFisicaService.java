package br.com.jonas.Spring_boot_essentials.service;


import br.com.jonas.Spring_boot_essentials.database.model.AlunosAntity;
import br.com.jonas.Spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.jonas.Spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.jonas.Spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.jonas.Spring_boot_essentials.dto.AvaliacaoFisicaDto;
import br.com.jonas.Spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import br.com.jonas.Spring_boot_essentials.exception.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

    private final IAlunosRepository iAlunosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public void criarAvaliacoesFisicas(AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFound, BadRequestException {
        AlunosAntity aluno = iAlunosRepository.findById(avaliacaoFisicaDto.getAlunoId())
                .orElseThrow(() -> new NotFound("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacoesFisicas = aluno.getAvaliacaoFisica();
        if (avaliacoesFisicas != null) {
            throw new BadRequestException("Avaliação física já encontrada para esse aluno");
        }
        avaliacoesFisicas = AvaliacoesFisicasEntity.builder()
                .peso(avaliacaoFisicaDto.getPeso())
                .altura(avaliacaoFisicaDto.getAltura())
                .porcentagemGorduraCorporal(avaliacaoFisicaDto.getPercentualGorduraCorporal())
                .build();

        aluno.setAvaliacaoFisica(avaliacoesFisicas);
        iAlunosRepository.save(aluno);
    }

    public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
        return avaliacoesFisicasRepository.getAllAvaliacoes();
    }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Integer page, Integer size){
        return avaliacoesFisicasRepository.getAllAvaliacoesPageable(PageRequest.of(page, size));
    }


}