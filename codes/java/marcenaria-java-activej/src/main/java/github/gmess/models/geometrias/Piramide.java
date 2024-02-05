package github.gmess.models.geometrias;

import static github.gmess.utils.StringConsts.ALTURA;
import static github.gmess.utils.StringConsts.EMPTY;
import static github.gmess.utils.StringConsts.ESTRUTURA;
import static github.gmess.utils.StringConsts.GEOMETRIA;
import static github.gmess.utils.StringConsts.LADO;
import static github.gmess.utils.StringConsts.MATERIAL;
import static github.gmess.utils.StringConsts.PIRAMIDE;

import github.gmess.exceptions.Notification;
import github.gmess.models.Material;
import github.gmess.utils.NumberUtils;
import java.util.Collections;
import java.util.Map;

/** Piramide */
public record Piramide(
    String geometria, String estrutura, Material material, String lado, String altura)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return PIRAMIDE;
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double lado = NumberUtils.stringToDouble(this.lado);
    final double altura = NumberUtils.stringToDouble(this.altura);

    return Math.pow(lado, 2) + 4 * (lado * altura / 2);
  }

  @Override
  public final Map<String, String> getMap() {
    return Collections.unmodifiableMap(
        Map.of(GEOMETRIA, geometria, ESTRUTURA, estrutura, LADO, lado, ALTURA, altura));
  }

  public static Piramide from(Map<String, String> map) {
    final var materialStr = map.getOrDefault(MATERIAL, EMPTY);
    final var material = Material.from(materialStr);

    if (material.isRight()) throw Notification.with(material.getRight());

    return new Piramide(
        map.getOrDefault(GEOMETRIA, EMPTY),
        map.getOrDefault(ESTRUTURA, EMPTY),
        material.getLeft(),
        map.getOrDefault(LADO, EMPTY),
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
