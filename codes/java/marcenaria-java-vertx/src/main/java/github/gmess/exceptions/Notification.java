package github.gmess.exceptions;

import java.util.Set;

public class Notification extends NoStackTraceException {
  protected final Set<Err> errors;

  protected Notification(final String message, final Set<Err> errors) {
    super(message);
    this.errors = errors;
  }

  public Notification add(final String error) {
    errors.add(new Err(error));
    return this;
  }

  public Notification add(final Err error) {
    errors.add(error);
    return this;
  }

  public Set<Err> getErrors() {
    return errors;
  }

  public static Notification with(final Err error) {
    return new Notification(error.message(), Set.of(error));
  }

  public static Notification with(final String msg, final Set<Err> errors) {
    return new Notification(msg, errors);
  }
}
