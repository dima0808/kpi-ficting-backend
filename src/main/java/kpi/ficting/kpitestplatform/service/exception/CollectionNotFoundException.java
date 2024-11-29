package kpi.ficting.kpitestplatform.service.exception;

public class CollectionNotFoundException extends RuntimeException {

  public CollectionNotFoundException(String name) {
    super("Collection with name " + name + " not found");
  }
}
