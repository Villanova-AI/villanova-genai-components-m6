package ai.villanova.themis.server.repository;

import ai.villanova.themis.server.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SourceRepository extends JpaRepository<SourceEntity, Long> {
    Optional<SourceEntity> findByName(String name);
    boolean existsByName(String name);
}
