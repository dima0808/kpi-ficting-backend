package kpi.ficting.kpitestplatform.service;

import java.util.List;
import java.util.UUID;
import kpi.ficting.kpitestplatform.domain.Question;

public interface QuestionService {

  List<Question> createAll(List<Question> questions);

  List<Question> findByTestId(UUID testId);

  List<Question> findByCollectionName(String collectionName);
}
