package kpi.ficting.kpitestplatform.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseEntryDto {

  private QuestionDto question;
  private List<Long> answerIds;
}
