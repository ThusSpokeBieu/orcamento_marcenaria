package github.com.marcenariaspring.config;

import github.com.marcenariaspring.models.Notification;
import jakarta.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

  @ExceptionHandler(value = {MethodArgumentNotValidException.class, ValidationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Mono<ResponseEntity<Notification>> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException err) {
    List<String> errors = new ArrayList<>();

    for (FieldError error : err.getBindingResult().getFieldErrors()) {
      errors.add(
          String.format(
              "%s -> '%s' é um valor inválido; %s.",
              error.getField(), error.getRejectedValue(), error.getDefaultMessage()));
    }

    for (ObjectError error : err.getBindingResult().getGlobalErrors()) {
      errors.add(String.format("%s -> %s.", error.getObjectName(), error.getDefaultMessage()));
    }

    Notification notification =
        new Notification(
            String.format("Existem %d argumentos inválidos", err.getErrorCount()),
            HttpStatus.BAD_REQUEST,
            errors);

    return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, notification.toString()));
  }
}
