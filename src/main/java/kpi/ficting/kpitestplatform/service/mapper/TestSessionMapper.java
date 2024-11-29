package kpi.ficting.kpitestplatform.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import kpi.ficting.kpitestplatform.repository.entity.ResponseEntry;
import kpi.ficting.kpitestplatform.repository.entity.TestSession;
import kpi.ficting.kpitestplatform.dto.ResponseEntryDto;
import kpi.ficting.kpitestplatform.dto.TestSessionDto;
import kpi.ficting.kpitestplatform.dto.TestSessionListDto;
import kpi.ficting.kpitestplatform.util.TestUtils;

public interface TestSessionMapper {

  default TestSession toTestSession(TestSessionDto testSessionDto) {
    return TestSession.builder()
        .sessionId(testSessionDto.getSessionId())
        .studentGroup(testSessionDto.getStudentGroup())
        .studentName(testSessionDto.getStudentName())
        .build();
  }

  default TestSessionDto toTestSessionDto(TestSession testSession, boolean includeResponses) {
    return toTestSessionDto(testSession, includeResponses, false);
  }

  default TestSessionDto toTestSessionDto(TestSession testSession, boolean includeResponses,
      boolean isAdmin) {
    return TestSessionDto.builder()
        .studentGroup(testSession.getStudentGroup())
        .studentName(testSession.getStudentName())
        .startedAt(testSession.getStartedAt())
        .finishedAt(testSession.getFinishedAt())
        .currentQuestionIndex(testSession.getCurrentQuestionIndex())
        .responses(includeResponses ?
            toResponseEntryDtoList(testSession.getResponses(), isAdmin) : null)
        .mark((isAdmin && testSession.getFinishedAt() != null) ?
            testSession.getResponses().stream()
                .mapToInt(TestUtils::calculateMark)
                .sum() : null)
        .build();
  }

  default List<TestSessionDto> toTestSessionDto(List<TestSession> testSessions,
      boolean includeResponses) {
    return testSessions.stream()
        .map(testSession -> toTestSessionDto(testSession, includeResponses, true))
        .collect(Collectors.toList());
  }

  default TestSessionListDto toTestSessionListDto(List<TestSession> testSessions,
      boolean includeResponses) {
    return TestSessionListDto.builder()
        .sessions(toTestSessionDto(testSessions, includeResponses))
        .build();
  }

  List<ResponseEntryDto> toResponseEntryDtoList(List<ResponseEntry> responses, boolean isAdmin);
}
