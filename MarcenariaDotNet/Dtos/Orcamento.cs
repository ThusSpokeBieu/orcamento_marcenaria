using System.Text.Json.Serialization;
using MarcenariaDotNet.Utils;

namespace MarcenariaDotNet.Dtos;

struct Orcamento
{
  public string Movel { get; init; }
  public string Material { get; init; }
  [JsonConverter(typeof(RealConverter))]
  public double ValorTotal { get; init; }
  public EstruturaValor[] Estruturas { get; init; }

  public static Orcamento From(MovelDto movel) {
    var estruturas = movel.Geometrias.Select(geometria => EstruturaValor.From(geometria, movel.Material)).ToArray();

    return new Orcamento {
      Movel = movel.Movel,
      Material = movel.Material,
      Estruturas = estruturas,
      ValorTotal = estruturas.Sum(e => e.Valor)
    };
  }
}
