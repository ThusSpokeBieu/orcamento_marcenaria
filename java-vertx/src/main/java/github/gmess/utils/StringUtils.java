package github.gmess.utils;

import java.text.DecimalFormat;
import java.text.Normalizer;

public class StringUtils {
  private final static DecimalFormat rsFormatter = new DecimalFormat("R$ ###########0.00");
  public static String doubleToRS(Double value) {
    return rsFormatter.format(value).replace('.', ',');
  }

  public static String onlyNumbers(String input) {
    return input.replaceAll("[^\\d,.]", "");
  }

  public static String normalize(final String text) {
    return Normalizer
      .normalize(text, Normalizer.Form.NFD)
      .replaceAll("[^\\p{ASCII}]", "")
      .replaceAll("[^\\p{Alpha}]", "");
  }
}
