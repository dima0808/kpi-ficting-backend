package kpi.ficting.kpitestplatform.service.exception;

import java.util.UUID;

public class TestNotFoundException extends RuntimeException {

  public TestNotFoundException(UUID id) {
    super("Test with id " + id + " not found");
  }
}
