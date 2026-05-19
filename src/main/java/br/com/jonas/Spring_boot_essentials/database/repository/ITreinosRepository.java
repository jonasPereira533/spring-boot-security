package br.com.jonas.Spring_boot_essentials.database.repository;

import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import br.com.jonas.Spring_boot_essentials.database.model.TreinosAntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITreinosRepository
        extends JpaRepository<TreinosAntity, Integer> {

    Optional<TreinosAntity> findByNomeAndAlunoId(String nome, Integer alunoId);

}
