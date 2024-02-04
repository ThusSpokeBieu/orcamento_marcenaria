package github.com.marcenariaspring.models;

import java.time.Instant;
import java.util.List;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Notification {
  private final Instant timestamp;
  private final int status;
  private final String error;
  private final String message;
  private final List<String> errors;

  public Notification(final String message, final HttpStatus status, final List<String> errors) {
    this.timestamp = Instant.now();
    this.status = status.value();
    this.error = status.getReasonPhrase();
    this.message = message;
    this.errors = errors;
  }
}
