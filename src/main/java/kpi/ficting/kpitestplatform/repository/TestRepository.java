package kpi.ficting.kpitestplatform.repository;

import java.util.UUID;
import kpi.ficting.kpitestplatform.repository.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID> {

}
