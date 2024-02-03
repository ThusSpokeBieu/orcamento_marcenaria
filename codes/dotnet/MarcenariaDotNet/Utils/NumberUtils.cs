using System.Globalization;
using System.Text.RegularExpressions;

namespace MarcenariaDotNet.Utils;

public static class NumberUtils 
{
  public const double TWO_PI = Math.PI * 2;
  public const double FOUR_PI = Math.PI * 4;

  public static double StrDouble(string str) 
  {
    str = Regex.Replace(str, "[^0-9,.]", "");
    str = str.Replace(',', '.');
    if (double.TryParse(str, out double value))
    {
      return value;
    } 

    return 0;
  }


  public static string DoubleToStr(double doub) 
  { 
    string str = doub.ToString("F2", CultureInfo.InvariantCulture);
    return str.Replace('.', ',');
  }

}
