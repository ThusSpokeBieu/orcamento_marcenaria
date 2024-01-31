package github.gmess.models.geometrias;

import github.gmess.utils.NumberUtils;

/**
 * Esfera
 */
public record Esfera(
  String geometria,
  String estrutura,
  String raio
) implements Geometria {
  
  @Override
  public final String getGeometria() {
    return "esfera";
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double raio = NumberUtils.centimetersToDouble(this.raio);

    return NumberUtils.FOUR_PI * Math.pow(raio, 2);
  }
 
}
