using System.Text.Json.Serialization;
using MarcenariaDotNet.Utils;

namespace MarcenariaDotNet.Dtos.Geometrias;

public struct Paralelepipedo : Geometria
{
  public String Estrutura { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double Altura { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double Comprimento { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double Largura { get; init; }

  double Geometria.GetArea()
  {
     return 2 * Comprimento * Largura + Comprimento * Altura + Largura * Altura;
  }

  string Geometria.GetEstrutura()
  {
    return Estrutura;
  }

  string Geometria.GetGeometria()
  {
    return "paralelepipedo";
  }
}
