package github.gmess.utils;

import java.text.DecimalFormat;
import java.text.Normalizer;

/** StrUtils */
public abstract class StrUtils {
  private static final DecimalFormat dcFormatter = new DecimalFormat("#########,##0.00");
  private static final String rs = "R$ ";
  private static final String cm = "cm";

  public static String doubleToReal(final double aDouble) {
    return "%s%s".formatted(rs, dcFormatter.format(aDouble));
  }

  public static String doubleToCentimeter(final double aDouble) {
    return "%s%s".formatted(dcFormatter.format(aDouble), cm);
  }

  public static String normalizeString(String input) {
    String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
    normalizedString = normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    normalizedString = normalizedString.replaceAll("[^a-zA-Z]", "").toLowerCase();
    return normalizedString;
  }
}
