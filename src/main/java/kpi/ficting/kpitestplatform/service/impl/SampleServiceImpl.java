package kpi.ficting.kpitestplatform.service.impl;

import java.util.List;
import java.util.UUID;
import kpi.ficting.kpitestplatform.domain.Sample;
import kpi.ficting.kpitestplatform.repository.SampleRepository;
import kpi.ficting.kpitestplatform.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

  private final SampleRepository sampleRepository;

  @Override
  public List<Sample> findByTestId(UUID testId) {
    return sampleRepository.findByTestId(testId);
  }
}
