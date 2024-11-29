package kpi.ficting.kpitestplatform.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SampleDto {

  private Long id;

  @NotNull(message = "Points is mandatory")
  @Min(value = 1, message = "Points must be greater than 0")
  private Integer points;

  @NotNull(message = "Questions count is mandatory")
  @Min(value = 1, message = "Questions count must be greater than 0")
  private Integer questionsCount;

  @NotBlank(message = "Collection name is mandatory")
  private String collectionName;
}
