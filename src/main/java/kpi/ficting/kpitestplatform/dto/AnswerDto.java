package kpi.ficting.kpitestplatform.dto;

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

  private Boolean isCorrect;

  @Size(min = 1, max = 250, message = "Answer must be between 1 and 250 characters")
  private String content;

  @Size(min = 1, max = 250, message = "Answer must be between 1 and 250 characters")
  private String leftOption;

  @Size(min = 1, max = 250, message = "Answer must be between 1 and 250 characters")
  private String rightOption;
}