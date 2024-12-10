package kpi.ficting.kpitestplatform.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.validation.constraints.Size;
import kpi.ficting.kpitestplatform.validation.ValidAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ValidAnswer
public class AnswerDto {

  private Long id;

  @JsonPropertyDescription("Is the answer correct. Only for SINGLE_CHOICE and MULTIPLE_CHOICES question types")
  private Boolean isCorrect;

  @Size(min = 1, max = 250, message = "Answer must be between 1 and 250 characters")
  @JsonPropertyDescription("The answer to the question. Only for SINGLE_CHOICE and MULTIPLE_CHOICES question types")
  private String content;

  @Size(min = 1, max = 250, message = "Answer must be between 1 and 250 characters")
  @JsonPropertyDescription("The left option of the correct pair. Only for MATCHING question type")
  private String leftOption;

  @Size(min = 1, max = 250, message = "Answer must be between 1 and 250 characters")
  @JsonPropertyDescription("The right option of the correct pair. Only for MATCHING question type")
  private String rightOption;
}
