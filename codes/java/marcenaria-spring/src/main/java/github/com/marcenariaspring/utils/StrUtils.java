package github.com.marcenariaspring.utils;

import java.text.DecimalFormat;
import java.text.Normalizer;

public class StrUtils {

  private static final DecimalFormat cmFormatter = new DecimalFormat("#########,##0.00");

  public static Double strToDouble(final String str) {
    return Double.parseDouble(str.replaceAll("[^\\d.,]", "").replaceAll(",", "."));
  }

  public static String doubleToStrCm(final Double aDouble) {
    return cmFormatter.format(aDouble) + "cm";
  }

  public static String doubleToReal(final Double aDouble) {
    return "R$" + cmFormatter.format(aDouble);
  }

  public static String normalizeString(String input) {
    String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
    normalizedString = normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    normalizedString = normalizedString.replaceAll("[^a-zA-Z]", "");
    normalizedString = normalizedString.toUpperCase();
    return normalizedString;
  }
}
