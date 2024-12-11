package kpi.ficting.kpitestplatform.service.impl;

import java.util.Map;
import kpi.ficting.kpitestplatform.common.QuestionType;
import kpi.ficting.kpitestplatform.dto.QuestionListDto;
import kpi.ficting.kpitestplatform.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

  private final ChatModel chatModel;

  @Value("classpath:templates/get-questions-for-test.st")
  private Resource questionsPrompt;

  @Override
  public QuestionListDto generateQuestions(String theme, QuestionType questionType, int points,
      int questionsCount) {
    BeanOutputConverter<QuestionListDto> converter = new BeanOutputConverter<>(QuestionListDto.class);
    String format = converter.getFormat();
    PromptTemplate promptTemplate = new PromptTemplate(questionsPrompt);
    Prompt prompt = promptTemplate.create(Map.of(
        "theme", theme,
        "questionType", questionType.getDisplayName(),
        "difficulty", points < 2 ? "easy" : points < 4 ? "medium" : "hard",
        "questionsCount", questionsCount,
        "format", format
    ));
    ChatResponse response = chatModel.call(prompt);
    QuestionListDto questions = converter.convert(response.getResult().getOutput().getContent());
    if (questions == null) {
      throw new RuntimeException("Failed to get questions");
    }
    questions.getQuestions().forEach(question -> question.setPoints(points));
    return questions;
  }
}
