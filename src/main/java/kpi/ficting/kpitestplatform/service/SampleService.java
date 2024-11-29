package kpi.ficting.kpitestplatform.service;

import java.util.List;
import java.util.UUID;
import kpi.ficting.kpitestplatform.repository.entity.Sample;

public interface SampleService {

  List<Sample> findByTestId(UUID testId);
}
