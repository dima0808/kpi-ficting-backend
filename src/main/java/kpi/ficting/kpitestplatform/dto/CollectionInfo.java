package kpi.ficting.kpitestplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CollectionInfo {

  private Long id;
  private String name;
  private Integer questionsCount;
}
