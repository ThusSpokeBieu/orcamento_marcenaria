package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.Material;
import github.gmess.utils.MaterialSerializer;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "cubo")
public record Cubo(@JsonAttribute(name = "estrutura") String estruturaCubica,
    @JsonAttribute(converter = MaterialSerializer.class) Material material, String lado)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return "cubo";
  }

  @Override
  public final String getEstrutura() {
    return this.estruturaCubica();
  }

  @Override
  public final double getArea() {
    final double lado = NumberUtils.centimetersToDouble(this.lado);
    return 6 * Math.pow(lado, 2);
  }

  @Override
  public double getPreco() {
    return getArea() * material.getPrecoBase();
  }

  @Override
  public String getMaterial() {
    return material.getNomeAsString();
  }
}
