package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "esfera")
public record Esfera(
  @JsonAttribute(name = "estrutura")
  String estruturaEsferica,
  String raio
) implements Geometria {

  @Override
  public final String getGeometria() {
    return "esfera";
  }

  @Override
  public final String getEstrutura() {
    return this.estruturaEsferica();
  }

  @Override
  public final double getArea() {
    final double raio = NumberUtils.centimetersToDouble(this.raio);

    return NumberUtils.FOUR_PI * Math.pow(raio, 2);
  }

}
