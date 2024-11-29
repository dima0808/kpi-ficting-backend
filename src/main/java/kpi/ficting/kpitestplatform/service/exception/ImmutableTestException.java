package kpi.ficting.kpitestplatform.service.exception;

import java.util.UUID;

public class ImmutableTestException extends RuntimeException {

  public ImmutableTestException(UUID id, String reason) {
    super("Test with id " + id + " is immutable: " + reason);
  }
}
