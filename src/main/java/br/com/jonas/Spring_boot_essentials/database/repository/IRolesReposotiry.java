package br.com.jonas.Spring_boot_essentials.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.jonas.Spring_boot_essentials.database.model.RolesEntity;
import java.util.Optional;

public interface IRolesReposotiry extends JpaRepository<RolesEntity, Integer> {

    Optional<RolesEntity> findByNome(String role);
}
