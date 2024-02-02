using System.Text.Json.Serialization;
using MarcenariaDotNet.Utils;

namespace MarcenariaDotNet.Dtos.Geometrias;

public struct Cilindro : Geometria
{

  public String Estrutura { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double RaioBase { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double Altura { get; init; }

  double Geometria.GetArea()
  {
    return NumberUtils.TWO_PI * RaioBase * (RaioBase + Altura);
  }

  string Geometria.GetEstrutura()
  {
    return Estrutura;
  }

  string Geometria.GetGeometria()
  {
     return "cilindro";
  }
}
