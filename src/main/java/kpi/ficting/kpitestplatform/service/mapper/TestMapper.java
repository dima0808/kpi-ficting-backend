package kpi.ficting.kpitestplatform.service.mapper;

import static kpi.ficting.kpitestplatform.util.TestUtils.getFinishedSessions;
import static kpi.ficting.kpitestplatform.util.TestUtils.getMaxScore;
import static kpi.ficting.kpitestplatform.util.TestUtils.getQuestionsCount;
import static kpi.ficting.kpitestplatform.util.TestUtils.getStartedSessions;

import java.util.List;
import kpi.ficting.kpitestplatform.repository.entity.Test;
import kpi.ficting.kpitestplatform.dto.TestDto;
import kpi.ficting.kpitestplatform.dto.TestInfo;
import kpi.ficting.kpitestplatform.dto.TestListInfo;

public interface TestMapper {

  default TestInfo toTestInfo(Test test, boolean isAdmin) {
    return TestInfo.builder()
        .id(test.getId().toString())
        .name(test.getName())
        .openDate(test.getOpenDate())
        .deadline(test.getDeadline())
        .minutesToComplete(test.getMinutesToComplete())
        .maxScore(getMaxScore(test.getQuestions(), test.getSamples()))
        .questionsCount(getQuestionsCount(test.getQuestions(), test.getSamples()))
        .startedSessions(isAdmin ? getStartedSessions(test.getSessions()) : null)
        .finishedSessions(isAdmin ? getFinishedSessions(test.getSessions()) : null)
        .build();
  }

  default TestInfo toTestInfo(Test test) {
    return toTestInfo(test, false);
  }

  default List<TestInfo> toTestInfo(List<Test> tests, boolean isAdmin) {
    return tests.stream()
        .map((test) -> toTestInfo(test, isAdmin))
        .toList();
  }

  default TestListInfo toTestListInfo(List<Test> tests, boolean isAdmin) {
    return TestListInfo.builder()
        .tests(toTestInfo(tests, isAdmin))
        .build();
  }

  Test toTest(TestDto testDto);
}
