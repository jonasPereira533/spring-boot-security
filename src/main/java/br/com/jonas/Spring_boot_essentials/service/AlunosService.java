package br.com.jonas.Spring_boot_essentials.service;

import br.com.jonas.Spring_boot_essentials.database.model.AlunosAntity;
import br.com.jonas.Spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.jonas.Spring_boot_essentials.database.model.TreinosAntity;
import br.com.jonas.Spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.jonas.Spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.jonas.Spring_boot_essentials.database.repository.ITreinosRepository;
import br.com.jonas.Spring_boot_essentials.dto.AlunoDto;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import br.com.jonas.Spring_boot_essentials.exception.NotFound;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunosService {

    private final IAvaliacoesFisicasRepository avaliacaoFisicasRepository;
    private final ITreinosRepository treinosRepository;
    private final IAlunosRepository alunosRepository;

    public void criarAluno(AlunoDto alunoDto) throws BadRequestException {
        AlunosAntity aluno = alunosRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

        if (aluno != null){
            throw new BadRequestException("aluno já cadastrado");
        }

        alunosRepository.save(AlunosAntity.builder()
                .nome(alunoDto.getNome())
                .email(alunoDto.getEmail())
                .build());
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId)throws NotFound {
        AlunosAntity alunos = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFound("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacao = alunos.getAvaliacaoFisica();
        if  (avaliacao == null){
            throw new NotFound("Avaliação física não encontrada");
        }
        return avaliacao;
    }


    @Transactional (rollbackFor = Exception.class)
    public void deletarAluno (Integer alunoId) throws NotFound{
        AlunosAntity alunos = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFound("Aluno não encontrado"));

        // aqui o transactional vai começar a transação
        // 1. deletar todos os treinos relacionados a esse aluno
        List<Integer> treinosAlunoIds = alunos.getTreino().stream()
                .map(TreinosAntity::getId)
                        .toList();
        treinosRepository.deleteAllById(treinosAlunoIds);


        // 2. deletar o aluno
        alunosRepository.deleteById(alunoId);

        // caso de algum erro inesperado em qualquer lugar ele vai dar um rollback em tudo
        // 3. deletar as avaliações físicas do aluno
        avaliacaoFisicasRepository.deleteById(alunos.getAvaliacaoFisica().getId());
    }

}
