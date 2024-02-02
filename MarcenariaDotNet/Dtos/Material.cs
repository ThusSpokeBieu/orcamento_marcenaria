namespace MarcenariaDotNet.Dtos;

public static class Material 
{
  public static double PrecoPinho { get; } = 0.10;
  public static double PrecoCarvalho { get; } = 0.30;
  public static double PrecoEbano { get; } = 5.00;

  public static double CalculaPreco(double area, string material) {
   return material switch
   {
     var m when m.Equals("pinho", StringComparison.OrdinalIgnoreCase) => area * PrecoPinho,
     var m when m.Equals("carvalho", StringComparison.OrdinalIgnoreCase) => area * PrecoCarvalho,  
     var m when m.Equals("ebano", StringComparison.OrdinalIgnoreCase) => area * PrecoEbano,
     _ => throw new Exception("Material inválido"),
   }; 
  }
}

