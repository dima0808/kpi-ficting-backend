package kpi.ficting.kpitestplatform.config.exception;

public class InvalidJwtException extends RuntimeException {

  public InvalidJwtException() {
    super("Invalid JWT token");
  }
}
