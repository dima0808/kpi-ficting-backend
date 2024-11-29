package kpi.ficting.kpitestplatform.service.exception;

public class TestSessionAlreadyExistsException extends RuntimeException {

  public TestSessionAlreadyExistsException(String studentGroup, String studentName) {
    super("Test session with student " + studentGroup + " " + studentName + " already exists");

  }
}
