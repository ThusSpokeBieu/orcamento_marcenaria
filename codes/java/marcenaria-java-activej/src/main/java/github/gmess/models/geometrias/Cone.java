package github.gmess.models.geometrias;

import static github.gmess.utils.StringConsts.ALTURA;
import static github.gmess.utils.StringConsts.CONE;
import static github.gmess.utils.StringConsts.EMPTY;
import static github.gmess.utils.StringConsts.ESTRUTURA;
import static github.gmess.utils.StringConsts.GEOMETRIA;
import static github.gmess.utils.StringConsts.MATERIAL;
import static github.gmess.utils.StringConsts.RAIO_BASE;

import github.gmess.exceptions.Notification;
import github.gmess.models.Material;
import github.gmess.utils.NumberUtils;
import java.util.Collections;
import java.util.Map;

public record Cone(
    String geometria, String estrutura, Material material, String raioBase, String altura)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return CONE;
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double raioBase = NumberUtils.stringToDouble(this.raioBase);
    final double altura = NumberUtils.stringToDouble(this.altura);

    return NumberUtils.ONE_THIRD_PI * raioBase * altura;
  }

  @Override
  public final Map<String, String> getMap() {
    return Collections.unmodifiableMap(
        Map.of(GEOMETRIA, geometria, ESTRUTURA, estrutura, RAIO_BASE, raioBase, ALTURA, altura));
  }

  public static Cone from(Map<String, String> map) {
    final var materialStr = map.getOrDefault(MATERIAL, EMPTY);
    final var material = Material.from(materialStr);

    if (material.isRight()) throw Notification.with(material.getRight());

    return new Cone(
        map.getOrDefault(GEOMETRIA, EMPTY),
        map.getOrDefault(ESTRUTURA, EMPTY),
        material.getLeft(),
        map.getOrDefault(RAIO_BASE, EMPTY),
        map.getOrDefault(ALTURA, EMPTY));
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
