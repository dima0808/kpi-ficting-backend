package kpi.ficting.kpitestplatform.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import kpi.ficting.kpitestplatform.validation.ValidQuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionDto {

  private Long id;

  @Size(min = 1, max = 250, message = "Question must be between 1 and 250 characters")
  private String content;

  @NotNull(message = "Points is mandatory")
  @Min(value = 1, message = "Question points must be greater than 0")
  private Integer points;

  @ValidQuestionType
  private String type;

  @Size(min = 2, message = "Question must have at least two answers")
  @Valid
  private List<AnswerDto> answers;
}
