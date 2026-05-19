package br.com.jonas.Spring_boot_essentials.database.repository;

import br.com.jonas.Spring_boot_essentials.database.model.AlunosAntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlunosRepository
        extends JpaRepository<AlunosAntity, Integer> {

    Optional<AlunosAntity> findByEmail(String email);

    @Query(value = "SELECT a FROM  AlunosAntity a JOIN FETCH a.avaliacaoFisica WHERE a.id = :alunoId")
    Optional<AlunosAntity> findByIdFetch(Integer alunoId);
}

