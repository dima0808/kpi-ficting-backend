package kpi.ficting.kpitestplatform.web;

import kpi.ficting.kpitestplatform.common.QuestionType;
import kpi.ficting.kpitestplatform.dto.QuestionListDto;
import kpi.ficting.kpitestplatform.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/questions")
@RequiredArgsConstructor
public class QuestionController {

  private final OpenAIService openAIService;

  @GetMapping("/generate")
  public ResponseEntity<QuestionListDto> generateQuestions(@RequestParam String theme,
      @RequestParam String questionType, @RequestParam int points,
      @RequestParam(defaultValue = "1") int questionsCount) {
    return ResponseEntity.ok(openAIService.generateQuestions(
        theme, QuestionType.valueOf(questionType.toUpperCase()), points, questionsCount));
  }
}
