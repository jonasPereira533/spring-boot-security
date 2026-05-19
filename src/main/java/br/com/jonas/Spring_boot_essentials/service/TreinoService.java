package br.com.jonas.Spring_boot_essentials.service;

import br.com.jonas.Spring_boot_essentials.database.model.AlunosAntity;
import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import br.com.jonas.Spring_boot_essentials.database.model.TreinosAntity;
import br.com.jonas.Spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.jonas.Spring_boot_essentials.database.repository.IExerciciosRepository;
import br.com.jonas.Spring_boot_essentials.database.repository.ITreinosRepository;
import br.com.jonas.Spring_boot_essentials.dto.TreinoDto;
import br.com.jonas.Spring_boot_essentials.exception.BadRequestException;
import br.com.jonas.Spring_boot_essentials.exception.NotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final ITreinosRepository treinosRepository;
    private final IAlunosRepository alunosRepository;
    private final IExerciciosRepository exerciciosRepository;

    public void criarTreino (TreinoDto treinoDto) throws NotFound, BadRequestException {
        Set<ExerciciosAntity> exercicios = new HashSet<>();

        AlunosAntity aluno = alunosRepository.findById(treinoDto.getAlunoId())
                .orElseThrow(()-> new NotFound("Aluno não encontrado"));

        TreinosAntity treino = treinosRepository.findByNomeAndAlunoId(treinoDto.getNome(), treinoDto.getAlunoId())
                    .orElse(null);
         if (treino != null) {

             throw new NotFound("Já existe um Treino com esse nome e aluno");
         }

         for (Integer exercicioId : treinoDto.getExerciciosIds()) {
             ExerciciosAntity exercicio = exerciciosRepository.findById(exercicioId)
                     .orElseThrow(()-> new NotFound(String.format("exercicio %s não encontrado", exercicioId)));

             exercicios.add(exercicio);
         }

         treino = TreinosAntity.builder()
                 .nome(treinoDto.getNome())
                 .aluno(aluno)
                 .execicios(exercicios)
                 .build();

         treinosRepository.save(treino);
    }
}
