package github.gmess.utils;


public abstract class NumberUtils {
  public static final double TWO_PI = 2 * Math.PI;
  public static final double FOUR_PI = 4 * Math.PI;
  
  public static final double centimetersToDouble(final String val) {
    return Double.valueOf(
      val
        .replace(",", ".")
        .replaceAll("[^0-9.]", "")
    );
  }
}
