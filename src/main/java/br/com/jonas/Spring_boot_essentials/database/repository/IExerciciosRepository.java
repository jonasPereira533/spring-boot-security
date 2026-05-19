package br.com.jonas.Spring_boot_essentials.database.repository;

import br.com.jonas.Spring_boot_essentials.database.model.ExerciciosAntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExerciciosRepository
        extends JpaRepository<ExerciciosAntity, Integer> {


    // formas de fazer consultas no banco de dados

    // 1 forma utilizando métodos do próprio spring data
    List<ExerciciosAntity> findAllByGrupoMuscular(String grupoMuscular);



    // 2 forma utilizando queries no banco, mas utilizando o nome da entidade
    @Query (value = """
        SELECT e 
        FROM ExerciciosAntity e 
        WHERE UPPER(e.grupoMuscular) = UPPER(:grupoMuscular)""")
    List<ExerciciosAntity> findAllByGrupoMuscularjpql(@Param("grupoMuscular")String grupoMuscular);


}
