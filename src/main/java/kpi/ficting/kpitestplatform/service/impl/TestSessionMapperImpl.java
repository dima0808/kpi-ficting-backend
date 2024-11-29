package kpi.ficting.kpitestplatform.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import kpi.ficting.kpitestplatform.repository.entity.Answer;
import kpi.ficting.kpitestplatform.repository.entity.ResponseEntry;
import kpi.ficting.kpitestplatform.dto.ResponseEntryDto;
import kpi.ficting.kpitestplatform.service.mapper.QuestionMapper;
import kpi.ficting.kpitestplatform.service.mapper.TestSessionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestSessionMapperImpl implements TestSessionMapper {

  private final QuestionMapper questionMapper;

  @Override
  public List<ResponseEntryDto> toResponseEntryDtoList(List<ResponseEntry> responses,
      boolean isAdmin) {
    return responses.stream()
        .map(responseEntry -> ResponseEntryDto.builder()
            .question(questionMapper.toQuestionDto(responseEntry.getQuestion(), isAdmin))
            .answerIds(responseEntry.getAnswers().stream()
                .map(Answer::getId)
                .collect(Collectors.toList()))
            .build())
        .collect(Collectors.toList());
  }
}
