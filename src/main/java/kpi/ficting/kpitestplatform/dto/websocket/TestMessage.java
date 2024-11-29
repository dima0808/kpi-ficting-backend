package kpi.ficting.kpitestplatform.dto.websocket;

import kpi.ficting.kpitestplatform.dto.QuestionDto;
import kpi.ficting.kpitestplatform.dto.TestSessionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class TestMessage {

  private TestMessageType type;
  private String content;
  private TestSessionDto testSession;
  private QuestionDto question;
}
