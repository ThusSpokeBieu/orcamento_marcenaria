using System.Text.Json.Serialization;
using MarcenariaDotNet.Utils;

namespace MarcenariaDotNet.Dtos.Geometrias;

public struct Esfera : Geometria
{
  public String Estrutura { get; init; }
  [JsonConverter(typeof(CentimeterConverter))]
  public double Raio { get; init; }

    double Geometria.GetArea()
    {
        return NumberUtils.FOUR_PI * Math.Pow(Raio, 2);
    }

    string Geometria.GetEstrutura()
    {
        return Estrutura;
    }

    string Geometria.GetGeometria()
    {
       return "esfera";
    }
}
