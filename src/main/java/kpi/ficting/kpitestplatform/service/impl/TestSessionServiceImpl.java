package kpi.ficting.kpitestplatform.service.impl;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import kpi.ficting.kpitestplatform.repository.entity.Answer;
import kpi.ficting.kpitestplatform.repository.entity.Question;
import kpi.ficting.kpitestplatform.repository.entity.ResponseEntry;
import kpi.ficting.kpitestplatform.repository.entity.Sample;
import kpi.ficting.kpitestplatform.repository.entity.Test;
import kpi.ficting.kpitestplatform.repository.entity.TestSession;
import kpi.ficting.kpitestplatform.repository.TestSessionRepository;
import kpi.ficting.kpitestplatform.service.TestService;
import kpi.ficting.kpitestplatform.service.TestSessionService;
import kpi.ficting.kpitestplatform.service.exception.TestSessionAlreadyExistsException;
import kpi.ficting.kpitestplatform.service.exception.TestSessionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestSessionServiceImpl implements TestSessionService {

  private final TestService testService;
  private final TestSessionRepository testSessionRepository;

  @Override
  public TestSession save(TestSession testSession) {
    return testSessionRepository.save(testSession);
  }

  @Override
  @Transactional
  public TestSession findByTestIdAndCredentials(UUID testId, String credentials,
      boolean finishedOnly) {
    String studentGroup = credentials.split(":")[0];
    String studentName = credentials.split(":")[1];
    TestSession testSession = testSessionRepository.findTestSessionByStudentGroupAndStudentNameAndTestId(
            studentGroup, studentName, testId)
        .orElseThrow(() -> new TestSessionNotFoundException(studentGroup, studentName));
    if (finishedOnly && testSession.getFinishedAt() == null) {
      throw new TestSessionNotFoundException(studentGroup, studentName);
    }
    Hibernate.initialize(testSession.getResponses());
    testSession.getResponses().forEach(r -> Hibernate.initialize(r.getQuestion().getAnswers()));
    return testSession;
  }

  @Override
  @Transactional
  public TestSession findByTestIdAndCredentials(UUID testId, String credentials) {
    return findByTestIdAndCredentials(testId, credentials, false);
  }

  @Override
  public TestSession findBySessionId(String sessionId) {
    return testSessionRepository.findTestSessionBySessionId(sessionId)
        .orElseThrow(() -> new TestSessionNotFoundException(sessionId));
  }

  @Override
  @Transactional
  public List<TestSession> findByTestId(UUID testId, boolean finishedOnly) {
    List<TestSession> sessions = testSessionRepository.findTestSessionsByTestId(testId);
    if (finishedOnly) {
      sessions = sessions.stream()
          .filter(s -> s.getFinishedAt() != null)
          .collect(Collectors.toList());
    }
    sessions.forEach(s -> {
      Hibernate.initialize(s.getResponses());
      s.getResponses().forEach(r -> Hibernate.initialize(r.getQuestion().getAnswers()));
    });
    return sessions;
  }

  @Override
  public TestSession startTestSession(UUID testId, TestSession testSession) {
    if (testSessionRepository.existsByStudentGroupAndStudentNameAndTestId(
        testSession.getStudentGroup(), testSession.getStudentName(), testId)) {
      throw new TestSessionAlreadyExistsException(
          testSession.getStudentGroup(), testSession.getStudentName());
    }
    Test test = testService.findById(testId);
    if (test.getOpenDate().isAfter(LocalDateTime.now())) {
      throw new IllegalStateException("Test is not open yet");
    }
    if (test.getDeadline().isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("Test is closed");
    }
    List<ResponseEntry> responses = new ArrayList<>();
    addTestQuestions(responses, test.getQuestions(), testSession);
    addSampleQuestions(responses, test.getSamples(), testSession);
    Collections.shuffle(responses);
    testSession.setResponses(responses);
    testSession.setTest(test);
    testSession.setStartedAt(LocalDateTime.now());
    testSession.setCurrentQuestionIndex(0);
    return testSessionRepository.save(testSession);
  }

  @Override
  @Transactional
  public Question nextQuestion(UUID testId, String credentials) {
    TestSession testSession = findByTestIdAndCredentials(testId, credentials);
    ResponseEntry responseEntry = nextResponseEntry(testSession);
    return responseEntry.getQuestion();
  }

  @Override
  @Transactional
  public TestSession saveAnswer(UUID testId, String credentials, List<Long> answerIds) {
    TestSession testSession = findByTestIdAndCredentials(testId, credentials);
    ResponseEntry responseEntry = nextResponseEntry(testSession);
    List<Answer> answers = new ArrayList<>(responseEntry.getQuestion().getAnswers());
    answers = answers.stream()
        .filter(a -> answerIds.contains(a.getId()))
        .collect(Collectors.toList());
    if (answers.isEmpty()) {
      throw new IllegalArgumentException("Answers must not be empty");
    }
    responseEntry.setAnswers(answers);
    testSession.setCurrentQuestionIndex(testSession.getCurrentQuestionIndex() + 1);
    return testSessionRepository.save(testSession);
  }

  @Override
  @Transactional
  public TestSession finishTestSession(UUID testId, String credentials) {
    TestSession testSession = findByTestIdAndCredentials(testId, credentials);
    testSession.setFinishedAt(LocalDateTime.now());
    return testSessionRepository.save(testSession);
  }

  private static void addTestQuestions(List<ResponseEntry> responses, List<Question> testQuestions,
      TestSession testSession) {
    for (Question question : testQuestions) {
      responses.add(ResponseEntry.builder()
          .question(question)
          .testSession(testSession)
          .build());
    }
  }

  private static void addSampleQuestions(List<ResponseEntry> responses, List<Sample> samples,
      TestSession testSession) {
    Random random = new Random();
    for (Sample sample : samples) {
      List<Question> sampleQuestions = sample.getCollection().getQuestions();
      List<Question> selectedQuestions = sampleQuestions.stream()
          .filter(q -> q.getPoints().equals(sample.getPoints()))
          .collect(Collectors.toList());
      for (int i = 0; i < sample.getQuestionsCount(); i++) {
        Question question = selectedQuestions.remove(random.nextInt(selectedQuestions.size()));
        responses.add(ResponseEntry.builder()
            .question(question)
            .testSession(testSession)
            .build());
      }
    }
  }

  private static ResponseEntry nextResponseEntry(TestSession testSession) {
    return testSession.getResponses().stream()
        .filter(r -> r.getAnswers().isEmpty())
        .findFirst()
        .orElseThrow(() -> new IllegalStateException("No more questions"));
  }
}
