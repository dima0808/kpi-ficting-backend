package kpi.ficting.kpitestplatform.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionListInfo {

  private List<CollectionInfo> collections;
}
