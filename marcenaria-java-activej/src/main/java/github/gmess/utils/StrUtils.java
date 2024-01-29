package github.gmess.utils;

import java.text.DecimalFormat;

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
}
