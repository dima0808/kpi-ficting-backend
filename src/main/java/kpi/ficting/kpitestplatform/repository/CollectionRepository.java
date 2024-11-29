package kpi.ficting.kpitestplatform.repository;

import java.util.Optional;
import kpi.ficting.kpitestplatform.domain.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

  Optional<Collection> findByName(String name);

  boolean existsByName(String name);

  void deleteByName(String collectionName);
}
