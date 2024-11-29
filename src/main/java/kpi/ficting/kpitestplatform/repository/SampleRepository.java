package kpi.ficting.kpitestplatform.repository;

import java.util.List;
import java.util.UUID;
import kpi.ficting.kpitestplatform.repository.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {

  List<Sample> findByTestId(UUID testId);
}
