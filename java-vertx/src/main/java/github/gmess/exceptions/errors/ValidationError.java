package github.gmess.exceptions.errors;

import java.lang.reflect.Type;

public class ValidationError extends Error {

  public ValidationError(final Type clazz, final String prop, final String msg) {
    super(
      String.format("ValidationError: %s, %s -> Erro de validação: %s",
                        clazz.getTypeName(),
                        prop,
                        msg)
         );
  }
}
