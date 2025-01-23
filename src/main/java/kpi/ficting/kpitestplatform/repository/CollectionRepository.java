package kpi.ficting.kpitestplatform.repository;

import java.util.Optional;
import kpi.ficting.kpitestplatform.repository.entity.Collection;
import kpi.ficting.kpitestplatform.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

  Optional<Collection> findByName(String name);

  boolean existsByNameAndAuthor(String name, User author);

  void deleteByName(String collectionName);
}
