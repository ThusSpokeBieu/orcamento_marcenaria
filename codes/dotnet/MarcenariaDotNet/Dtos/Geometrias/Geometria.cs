using System.Text.Json.Serialization;

namespace MarcenariaDotNet.Dtos.Geometrias;

[JsonPolymorphic(TypeDiscriminatorPropertyName = "geometria")]
[JsonDerivedType(typeof(Esfera), typeDiscriminator: "esfera")]
[JsonDerivedType(typeof(Cubo), typeDiscriminator: "cubo")]
[JsonDerivedType(typeof(Cilindro), typeDiscriminator: "cilindro")]
[JsonDerivedType(typeof(Paralelepipedo), typeDiscriminator: "paralelepipedo")]
//[JsonConverter(typeof(GeometriaConverter))]
public interface Geometria {
  public String GetGeometria();
  public String GetEstrutura();
  public double GetArea();
} 
