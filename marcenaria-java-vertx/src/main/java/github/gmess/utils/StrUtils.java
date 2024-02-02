package github.gmess.utils;

import java.text.DecimalFormat;
import java.text.Normalizer;

/**
 * StrUtils
 */
public abstract class StrUtils {
  private static final DecimalFormat dcFormatter = new DecimalFormat("#########,##0.00");
  private static final String rs = "R$ ";

  public static String doubleToReal(final double aDouble) {
    return new StringBuffer()
              .append(rs)
              .append(dcFormatter.format(aDouble))
              .toString();
  }

  public static String normalizeString(String input) {
    String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
    normalizedString = normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    normalizedString = normalizedString.replaceAll("[^a-zA-Z]", "");
    normalizedString = normalizedString.toUpperCase();
    return normalizedString;
  }
}
