package kpi.ficting.kpitestplatform.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import kpi.ficting.kpitestplatform.domain.Collection;
import kpi.ficting.kpitestplatform.repository.CollectionRepository;
import kpi.ficting.kpitestplatform.service.CollectionService;
import kpi.ficting.kpitestplatform.service.exception.CollectionAlreadyExistsException;
import kpi.ficting.kpitestplatform.service.exception.CollectionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

  private final CollectionRepository collectionRepository;

  @Override
  public List<Collection> findAll() {
    return collectionRepository.findAll();
  }

  @Override
  public Collection findByName(String collectionName) {
    return collectionRepository.findByName(collectionName)
        .orElseThrow(() -> new CollectionNotFoundException(collectionName));
  }

  @Override
  public Collection create(Collection collection) {
    if (collectionRepository.existsByName(collection.getName())) {
      throw new CollectionAlreadyExistsException(collection.getName());
    }
    return collectionRepository.save(collection);
  }

  @Override
  @Transactional
  public void delete(String collectionName) {
    collectionRepository.deleteByName(collectionName);
  }
}
