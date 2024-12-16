package kpi.ficting.kpitestplatform.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionListDto {

  @Valid
  @JsonPropertyDescription("List of questions")
  private List<QuestionDto> questions;
}
