using MarcenariaDotNet.Dtos.Geometrias;

namespace MarcenariaDotNet.Dtos;

struct EstruturaValor
{
  public string Estrutura { get; init; }
  public string Geometria { get; init; }
  public double Valor { get; init; }

  public static EstruturaValor From(Geometria geometria, string material) {
    return new EstruturaValor {
      Estrutura = geometria.GetEstrutura(),
      Geometria = geometria.GetGeometria(),
      Valor = Material.CalculaPreco(geometria.GetArea(), material)
    };
  }
}
