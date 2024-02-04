package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "geometria")
@JsonSubTypes({
  @JsonSubTypes.Type(
      value = Esfera.class,
      name = "esfera",
      names = {"ESFERA", "Esfera", "esfera"}),
  @JsonSubTypes.Type(
      value = Cubo.class,
      name = "cubo",
      names = {"CUBO", "Cubo", "cubo"}),
  @JsonSubTypes.Type(
      value = Cilindro.class,
      name = "cilindro",
      names = {"cilindro", "Cilindro", "CILINDRO", "Cilíndro", "cilíndro", "CILÍNDRO"}),
  @JsonSubTypes.Type(
      value = Paralelepipedo.class,
      name = "paralelepipedo",
      names = {
        "Paralelepipedo",
        "PARALELEPIPEDO",
        "paralelepipedo",
        "Paralelepípedo",
        "PARALELEPÍPEDO",
        "paralelepípedo"
      })
})
public interface Geometria {
  public abstract double getPreco();

  public abstract String getGeometria();

  public abstract String getEstrutura();

  public abstract String getMaterial();
}
