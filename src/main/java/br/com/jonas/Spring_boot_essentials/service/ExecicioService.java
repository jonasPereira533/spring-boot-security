package br.com.jonas.Spring_boot_essentials.service;


import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import br.com.jonas.Spring_boot_essentials.database.repository.IExerciciosRepository;
import br.com.jonas.Spring_boot_essentials.dto.ExerciciosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExecicioService {

    private final IExerciciosRepository iExerciciosRepository;

    public List<ExerciciosAntity> findAllExercicios() {
        return iExerciciosRepository.findAll();
    }

    public void save(ExerciciosDto exerciciosDto) {
        ExerciciosAntity exercicio = (ExerciciosAntity.builder()
                .nome(exerciciosDto.getNome())
                .grupoMuscular(exerciciosDto.getGrupoMuscular())
                .build());

        iExerciciosRepository.save(exercicio);
    }

    public List<ExerciciosAntity> getExercicioByGrupoMuscular(String grupoMuscular) {
        return iExerciciosRepository.findAllByGrupoMuscular(grupoMuscular);
    }
}
