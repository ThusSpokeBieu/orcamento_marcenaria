package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.Material;
import github.gmess.utils.MaterialSerializer;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "disco")
public record Disco(
    @JsonAttribute(name = "estrutura") String estruturaDisco,
    @JsonAttribute(converter = MaterialSerializer.class) Material material,
    String raio)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return "disco";
  }

  @Override
  public final String getEstrutura() {
    return this.estruturaDisco();
  }

  @Override
  public final double getArea() {
    final double raio = NumberUtils.centimetersToDouble(this.raio);

    return Math.PI * Math.pow(raio, 2);
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
