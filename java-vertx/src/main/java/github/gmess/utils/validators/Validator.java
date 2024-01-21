package github.gmess.utils.validators;

import github.gmess.exceptions.errors.RequiredError;
import github.gmess.exceptions.errors.ValidationError;
import github.gmess.utils.StringUtils;
import java.lang.reflect.Type;
import java.util.HashSet;
import github.gmess.exceptions.errors.Error;

public class Validator {

  public static void validateRequired(
    final Type clazz,
    final String prop,
    final String str,
    final HashSet<Error> errors) {

    if (str.isBlank()) errors.add(
      new RequiredError(clazz, prop)
                                 );
  }

  public static Double validateDouble(
    final Type clazz,
    final String prop,
    final String str,
    final HashSet<Error> errors) {

    try {
      var onlyNum = StringUtils.onlyNumbers(str);
      return Double.parseDouble(onlyNum);
    } catch ( final NumberFormatException err) {
      var msg = String.format("%s não dá para ser convertido em decimal, valide-o");
      errors.add(
        new ValidationError(clazz, prop, msg)
                );

      return 0.0;
    }

  }

}
