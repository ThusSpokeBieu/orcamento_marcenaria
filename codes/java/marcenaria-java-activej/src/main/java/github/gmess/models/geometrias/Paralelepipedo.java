package github.gmess.models.geometrias;

import github.gmess.utils.NumberUtils;

public record Paralelepipedo(
    String geometria,
    String estrutura,
    String altura,
    String comprimento,
    String largura
  ) implements Geometria {
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
    final double altura = NumberUtils.centimetersToDouble(this.largura);
    final double comprimento = NumberUtils.centimetersToDouble(this.comprimento);
    final double largura = NumberUtils.centimetersToDouble(this.altura);

    return 2 * (comprimento * largura + comprimento * altura + largura * altura);
  };
}
