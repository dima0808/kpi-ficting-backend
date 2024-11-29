package kpi.ficting.kpitestplatform.service.mapper.impl;

import java.util.List;
import kpi.ficting.kpitestplatform.domain.Collection;
import kpi.ficting.kpitestplatform.dto.CollectionDto;
import kpi.ficting.kpitestplatform.service.mapper.CollectionMapper;
import kpi.ficting.kpitestplatform.service.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectionMapperImpl implements CollectionMapper {

  private final QuestionMapper questionMapper;

  public Collection toCollection(CollectionDto collectionDto) {
    Collection collection = Collection.builder()
        .name(collectionDto.getName())
        .build();
    collection.setQuestions(
        collectionDto.getQuestions() == null || collectionDto.getQuestions().isEmpty() ? List.of()
            : questionMapper.toQuestionList(collectionDto.getQuestions(), collection));
    return collection;
  }
}
