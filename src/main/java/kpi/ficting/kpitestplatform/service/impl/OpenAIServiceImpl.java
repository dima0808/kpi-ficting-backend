package kpi.ficting.kpitestplatform.service.impl;

import java.util.Map;
import kpi.ficting.kpitestplatform.common.QuestionType;
import kpi.ficting.kpitestplatform.dto.QuestionDto;
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

  @Value("classpath:templates/get-question-for-test.st")
  private Resource questionPrompt;

  @Override
  public QuestionDto generateQuestion(String theme, QuestionType questionType, int points) {
    BeanOutputConverter<QuestionDto> converter = new BeanOutputConverter<>(QuestionDto.class);
    String format = converter.getFormat();
    PromptTemplate promptTemplate = new PromptTemplate(questionPrompt);
    Prompt prompt = promptTemplate.create(Map.of(
        "theme", theme,
        "questionType", questionType.getDisplayName(),
        "difficulty", points < 3 ? "easy" : points < 6 ? "medium" : "hard",
        "format", format
    ));
    ChatResponse response = chatModel.call(prompt);
    QuestionDto question = converter.convert(response.getResult().getOutput().getContent());
    if (question == null) {
      throw new RuntimeException("Failed to get question");
    }
    question.setPoints(points);
    return question;
  }
}
