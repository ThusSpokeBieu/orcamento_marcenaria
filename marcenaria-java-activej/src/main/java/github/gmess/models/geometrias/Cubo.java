package github.gmess.models.geometrias;

import github.gmess.utils.NumberUtils;

/**
 * Cubo
 */
public record Cubo(
  String geometria,
  String estrutura,
  String lado
) implements Geometria {
  
  @Override
  public final String getGeometria() {
    return "cubo";
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double lado = NumberUtils.centimetersToDouble(this.lado);
    return 6 * Math.pow(lado, 2);
  }

}
