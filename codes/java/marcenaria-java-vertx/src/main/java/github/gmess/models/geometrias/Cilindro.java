package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.Material;
import github.gmess.utils.MaterialSerializer;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "cilindro")
public record Cilindro(
    @JsonAttribute(name = "estrutura") String estruturaCilindrica,
    @JsonAttribute(name = "raio_base") String raioBase,
    @JsonAttribute(converter = MaterialSerializer.class) Material material,
    String altura)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return "cilindro";
  }

  @Override
  public final String getEstrutura() {
    return this.estruturaCilindrica();
  }

  @Override
  public final double getArea() {
    final double raioBase = NumberUtils.centimetersToDouble(this.raioBase);
    final double altura = NumberUtils.centimetersToDouble(this.altura);

    return NumberUtils.TWO_PI * raioBase * (raioBase + altura);
  }

  @Override
  public final double getPreco() {
    return getArea() * material.getPrecoBase();
  }

  @Override
  public String getMaterial() {
    return material.getNomeAsString();
  }
}
