package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.Material;
import github.gmess.utils.MaterialSerializer;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "cone")
public record Cone(
    @JsonAttribute(name = "estrutura") String estruturaCone,
    @JsonAttribute(converter = MaterialSerializer.class) Material material,
    @JsonAttribute(name = "raio_base") String raioBase,
    String altura)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return "cone";
  }

  @Override
  public final String getEstrutura() {
    return this.estruturaCone();
  }

  @Override
  public final double getArea() {
    final double raioBase = NumberUtils.centimetersToDouble(this.raioBase);
    final double altura = NumberUtils.centimetersToDouble(this.altura);

    return (1 / 3) * Math.PI * raioBase * altura;
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
