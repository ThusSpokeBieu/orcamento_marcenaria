using System.Text.Json.Serialization;
using MarcenariaDotNet.Utils;

namespace MarcenariaDotNet.Dtos.Geometrias;

public struct Cubo : Geometria
{
  public String Estrutura { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double Lado { get; init; }

    double Geometria.GetArea()
    {
        return Math.Sqrt(3) * Lado / 2;
    }

    string Geometria.GetEstrutura()
    {
        return Estrutura;
    }

    string Geometria.GetGeometria()
    {
       return "cubo";
    }
}
