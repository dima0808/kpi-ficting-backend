package kpi.ficting.kpitestplatform.config;

import jakarta.transaction.Transactional;
import kpi.ficting.kpitestplatform.common.QuestionType;
import kpi.ficting.kpitestplatform.repository.entity.Answer;
import kpi.ficting.kpitestplatform.repository.entity.Choice;
import kpi.ficting.kpitestplatform.repository.entity.Collection;
import kpi.ficting.kpitestplatform.repository.entity.MatchingPair;
import kpi.ficting.kpitestplatform.repository.entity.Question;
import kpi.ficting.kpitestplatform.repository.entity.Test;
import kpi.ficting.kpitestplatform.service.CollectionService;
import kpi.ficting.kpitestplatform.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private final TestService testService;
  private final CollectionService collectionService;

  @Override
  @Transactional
  public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
//    loadTestData();
//    loadCollectionData();
  }

  private void loadTestData() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    Test test = Test.builder()
        .name("simp test")
        .openDate(LocalDateTime.parse("01.01.2025 12:00", formatter))
        .deadline(LocalDateTime.parse("01.01.2025 15:00", formatter))
        .minutesToComplete(45)
        .build();

    Question question1 = Question.builder()
        .content("will you put a phone down?")
        .points(10)
        .type(QuestionType.MULTIPLE_CHOICES)
        .test(test)
        .build();
    Answer answer1_1 = Choice.builder()
        .content("yes")
        .isCorrect(false)
        .question(question1)
        .build();
    Answer answer1_2 = Choice.builder()
        .content("no")
        .isCorrect(true)
        .question(question1)
        .build();
    Answer answer1_3 = Choice.builder()
        .content("this is for my safety")
        .isCorrect(true)
        .question(question1)
        .build();
    question1.setAnswers(List.of(answer1_1, answer1_2, answer1_3));

    Question question2 = Question.builder()
        .content("will you put still water down?")
        .points(5)
        .type(QuestionType.SINGLE_CHOICE)
        .test(test)
        .build();
    Answer answer2_1 = Choice.builder()
        .content("yes")
        .isCorrect(false)
        .question(question2)
        .build();
    Answer answer2_2 = Choice.builder()
        .content("this is for those who know (skull emoji)")
        .isCorrect(true)
        .question(question2)
        .build();
    question2.setAnswers(List.of(answer2_1, answer2_2));

    Question question3 = Question.builder()
        .content("match the numbers with the letters")
        .points(15)
        .type(QuestionType.MATCHING)
        .test(test)
        .build();
    Answer answer3_1a = MatchingPair.builder()
        .leftOption("1")
        .rightOption("a")
        .isCorrect(true)
        .question(question3)
        .build();
    Answer answer3_1b = MatchingPair.builder()
        .leftOption("1")
        .rightOption("b")
        .isCorrect(false)
        .question(question3)
        .build();
    Answer answer3_2a = MatchingPair.builder()
        .leftOption("2")
        .rightOption("a")
        .isCorrect(false)
        .question(question3)
        .build();
    Answer answer3_2b = MatchingPair.builder()
        .leftOption("2")
        .rightOption("b")
        .isCorrect(true)
        .question(question3)
        .build();
    question3.setAnswers(List.of(answer3_1a, answer3_1b, answer3_2a, answer3_2b));

    test.setQuestions(List.of(question1, question2, question3));

    testService.create(test);
  }

  private void loadCollectionData() {
    Collection collection = Collection.builder()
        .name("simp collection")
        .build();

    collectionService.create(collection);
  }
}