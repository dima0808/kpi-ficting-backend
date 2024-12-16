package kpi.ficting.kpitestplatform.service;

import kpi.ficting.kpitestplatform.common.QuestionType;
import kpi.ficting.kpitestplatform.dto.QuestionListDto;

public interface OpenAIService {

  QuestionListDto generateQuestions(String theme, QuestionType questionsType, int points,
      int questionCount);
}
