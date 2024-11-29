package kpi.ficting.kpitestplatform.service.impl;

import static kpi.ficting.kpitestplatform.util.TestUtils.getFinishedSessions;
import static kpi.ficting.kpitestplatform.util.TestUtils.getStartedSessions;

import java.util.List;
import java.util.UUID;
import kpi.ficting.kpitestplatform.repository.entity.Test;
import kpi.ficting.kpitestplatform.repository.TestRepository;
import kpi.ficting.kpitestplatform.service.TestService;
import kpi.ficting.kpitestplatform.service.exception.ImmutableTestException;
import kpi.ficting.kpitestplatform.service.exception.TestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private final TestRepository testRepository;

  @Override
  public List<Test> findAll() {
    return testRepository.findAll();
  }

  @Override
  public Test findById(UUID testId) {
    return testRepository.findById(testId)
        .orElseThrow(() -> new TestNotFoundException(testId));
  }

  @Override
  public Test create(Test test) {
    test.setSessions(List.of());
    return testRepository.save(test);
  }

  @Override
  public Test save(Test test) {
    return testRepository.save(test);
  }

  @Override
  public Test update(UUID testId, Test test) {
    Test testToUpdate = findById(testId);
    if (getStartedSessions(test.getSessions()) != 0) {
      throw new ImmutableTestException(testId, "has started sessions");
    }
    if (getFinishedSessions(test.getSessions()) != 0) {
      throw new ImmutableTestException(testId, "has finished sessions");
    }
    testToUpdate.setName(test.getName());
    testToUpdate.setOpenDate(test.getOpenDate());
    testToUpdate.setDeadline(test.getDeadline());
    testToUpdate.setMinutesToComplete(test.getMinutesToComplete());

    test.getQuestions().forEach(question -> question.setTest(testToUpdate));
    testToUpdate.setQuestions(test.getQuestions());
    return testRepository.save(testToUpdate);
  }

  @Override
  public void deleteById(UUID testId) {
    testRepository.deleteById(testId);
  }
}
