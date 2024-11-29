package kpi.ficting.kpitestplatform.service.mapper.impl;

import kpi.ficting.kpitestplatform.domain.Test;
import kpi.ficting.kpitestplatform.dto.TestDto;
import kpi.ficting.kpitestplatform.service.mapper.QuestionMapper;
import kpi.ficting.kpitestplatform.service.mapper.SampleMapper;
import kpi.ficting.kpitestplatform.service.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestMapperImpl implements TestMapper {

  private final QuestionMapper questionMapper;
  private final SampleMapper sampleMapper;

  @Override
  public Test toTest(TestDto testDto) {
    Test test = Test.builder()
        .name(testDto.getName())
        .openDate(testDto.getOpenDate())
        .deadline(testDto.getDeadline())
        .minutesToComplete(testDto.getMinutesToComplete())
        .build();
    test.setQuestions(questionMapper.toQuestionList(testDto.getQuestions(), test));
    test.setSamples(sampleMapper.toSampleList(testDto.getSamples(), test));
    return test;
  }
}
