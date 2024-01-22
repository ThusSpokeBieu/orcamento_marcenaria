package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import github.com.marcenariaspring.models.materiais.Material;
import java.io.Serial;
import java.io.Serializable;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "geometria"
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = Esfera.class, name = "esfera", names = { "ESFERA", "Esfera", "esfera"}),
  @JsonSubTypes.Type(value = Cubo.class, name = "cubo", names = { "CUBO", "Cubo", "cubo"}),
  @JsonSubTypes.Type(value = Cilindro.class,
                     name = "cilindro",
                     names = {"cilindro", "Cilindro", "CILINDRO", "Cilíndro", "cilíndro",
    "CILÍNDRO"}),
  @JsonSubTypes.Type(value = Paralelepipedo.class, name = "paralelepipedo", names = { "Paralelepipedo",
    "PARALELEPIPEDO",
    "paralelepipedo",
    "Paralelepípedo", "PARALELEPÍPEDO", "paralelepípedo"})
})
@EqualsAndHashCode
public abstract class Geometria implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  protected String geometria;
  public abstract Double getPreco(final Material material);
  public abstract String getGeometria();
  public abstract String getEstrutura();
}
