package kpi.ficting.kpitestplatform.service.mapper;

import java.util.List;
import kpi.ficting.kpitestplatform.domain.Sample;
import kpi.ficting.kpitestplatform.domain.Test;
import kpi.ficting.kpitestplatform.dto.SampleDto;
import kpi.ficting.kpitestplatform.dto.SampleListDto;

public interface SampleMapper {

  default SampleDto toSampleDto(Sample sample) {
    return SampleDto.builder()
        .id(sample.getId())
        .points(sample.getPoints())
        .questionsCount(sample.getQuestionsCount())
        .collectionName(sample.getCollection().getName())
        .build();
  }

  default List<SampleDto> toSampleDto(List<Sample> samples) {
    return samples.stream()
        .map(this::toSampleDto)
        .toList();
  }

  default SampleListDto toSampleListDto(List<Sample> samples) {
    return SampleListDto.builder()
        .samples(toSampleDto(samples))
        .build();
  }

  List<Sample> toSampleList(List<SampleDto> sampleDtos, Test test);
}
