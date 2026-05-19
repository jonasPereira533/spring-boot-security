package br.com.jonas.Spring_boot_essentials.database.repository;

import br.com.jonas.Spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import br.com.jonas.Spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface IAvaliacoesFisicasRepository
        extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

    @Query(value = """
    SELECT 
        a.id  idAluno,
        a.nome  nomeAluno,
        af.id  idAvaliacao,
        af.peso  peso,
        af.altura  altura,
        af.porcentagem_gordura_corporal  percentualGorduraCorporal
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a 
    ON a.avaliacao_fisica_id = af.id
""", nativeQuery = true)

    List<AvaliacoesFisicasProjection> getAllAvaliacoes();


    @Query(value = """
    SELECT 
        a.id  idAluno,
        a.nome  nomeAluno,
        af.id  idAvaliacao,
        af.peso  peso,
        af.altura  altura,
        af.porcentagem_gordura_corporal  percentualGorduraCorporal
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a 
    ON a.avaliacao_fisica_id = af.id
""", nativeQuery = true,
            countQuery = """
    SELECT count(af.id)
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a
    ON a.avaliacao_fisica_id = af.id
""")
    Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Pageable pageable);
}

