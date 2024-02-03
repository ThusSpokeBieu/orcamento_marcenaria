package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "cilindro")
public record Cilindro(
  @JsonAttribute(name = "estrutura")
  String estruturaCilindrica,

  @JsonAttribute(name = "raio_base")
  String raioBase,

  String altura) implements Geometria {

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

    return NumberUtils.TWO_PI * raioBase * (raioBase * altura);
  }
}
