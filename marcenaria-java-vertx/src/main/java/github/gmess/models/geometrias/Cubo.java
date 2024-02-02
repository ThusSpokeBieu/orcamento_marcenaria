package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "cubo")
public record Cubo(
  @JsonAttribute(name = "estrutura")
  String estruturaCubica,
  String lado
) implements Geometria {

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

}
