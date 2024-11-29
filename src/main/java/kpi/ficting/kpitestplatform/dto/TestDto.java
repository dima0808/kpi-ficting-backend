package kpi.ficting.kpitestplatform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import kpi.ficting.kpitestplatform.validation.ValidQuestionSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ValidQuestionSet
public class TestDto {

  @NotBlank(message = "Test name is mandatory")
  @Size(min = 1, max = 100, message = "Test name must be between 1 and 100 characters")
  private String name;

  @NotNull(message = "Open date is mandatory")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
  private LocalDateTime openDate;

  @Future(message = "Deadline must be in the future")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
  private LocalDateTime deadline;

  @Min(value = 1, message = "Minutes to complete test must be greater than 0")
  private Integer minutesToComplete;

  @Valid
  private List<QuestionDto> questions;

  @Valid
  private List<SampleDto> samples;
}
