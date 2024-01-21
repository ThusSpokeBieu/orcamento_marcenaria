package github.gmess.exceptions.errors;

import java.lang.reflect.Type;

public class RequiredError extends Error {
  public RequiredError(final Type clazz, final String prop) {
    super(
      String.format("%s, %s -> Não pode ser vazio ou nulo",
                    clazz.getTypeName(),
                    prop)
         );
  }
}
