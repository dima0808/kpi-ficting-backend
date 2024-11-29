package kpi.ficting.kpitestplatform.service.mapper;

import java.util.List;
import kpi.ficting.kpitestplatform.repository.entity.Collection;
import kpi.ficting.kpitestplatform.dto.CollectionDto;
import kpi.ficting.kpitestplatform.dto.CollectionInfo;
import kpi.ficting.kpitestplatform.dto.CollectionListInfo;

public interface CollectionMapper {

  default CollectionInfo toCollectionInfo(Collection collection) {
    return CollectionInfo.builder()
        .id(collection.getId())
        .name(collection.getName())
        .questionsCount(collection.getQuestions().size())
        .build();
  }

  default List<CollectionInfo> toCollectionInfo(List<Collection> collections) {
    return collections.stream()
        .map(this::toCollectionInfo)
        .toList();
  }

  default CollectionListInfo toCollectionListInfo(List<Collection> collections) {
    return CollectionListInfo.builder()
        .collections(toCollectionInfo(collections))
        .build();
  }

  Collection toCollection(CollectionDto collectionDto);
}
