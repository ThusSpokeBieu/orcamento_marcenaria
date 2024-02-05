package github.gmess.utils;

import io.activej.json.JsonFunction;
import java.util.function.Function;

public abstract class SerializerUtils {
  public static final JsonFunction<String, Double> stringToDouble =
      centimeter -> NumberUtils.stringToDouble(centimeter);

  public static final Function<Double, String> toCentimeter =
      number -> StrUtils.doubleToCentimeter(number);

  public static final Function<Double, String> toReal = number -> StrUtils.doubleToReal(number);
}
