package github.gmess.models.geometrias;

import github.gmess.utils.NumberUtils;

public record Cilindro(
  String geometria,
  String estrutura, 
  
  String raioBase, 

  String altura) implements Geometria {

  @Override
  public final String getGeometria() {
    return "cilindro";
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double raioBase = NumberUtils.centimetersToDouble(this.raioBase);
    final double altura = NumberUtils.centimetersToDouble(this.altura);

    return NumberUtils.TWO_PI * raioBase * (raioBase * altura);
  }
} 
