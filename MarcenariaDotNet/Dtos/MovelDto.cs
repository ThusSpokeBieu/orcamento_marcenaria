namespace MarcenariaDotNet.Dtos;

using MarcenariaDotNet.Dtos.Geometrias;

public struct MovelDto
{ 
  public string Movel {get;init;}
  public string Material {get;init;}
  public Geometria[] Geometrias {get; init;}
}
