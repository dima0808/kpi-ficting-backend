package kpi.ficting.kpitestplatform.service;

import kpi.ficting.kpitestplatform.common.QuestionType;
import kpi.ficting.kpitestplatform.dto.QuestionDto;

public interface OpenAIService {

  QuestionDto generateQuestion(String theme, QuestionType questionType, int points);
}
