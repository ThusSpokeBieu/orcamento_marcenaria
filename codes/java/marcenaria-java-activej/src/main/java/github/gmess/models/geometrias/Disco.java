package github.gmess.models.geometrias;

import static github.gmess.utils.StringConsts.DISCO;
import static github.gmess.utils.StringConsts.EMPTY;
import static github.gmess.utils.StringConsts.ESTRUTURA;
import static github.gmess.utils.StringConsts.GEOMETRIA;
import static github.gmess.utils.StringConsts.MATERIAL;
import static github.gmess.utils.StringConsts.RAIO;

import github.gmess.exceptions.Notification;
import github.gmess.models.Material;
import github.gmess.utils.NumberUtils;
import java.util.Collections;
import java.util.Map;

/** Disco */
public record Disco(String geometria, String estrutura, Material material, String raio)
    implements Geometria {

  @Override
  public final String getGeometria() {
    return DISCO;
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double raio = NumberUtils.stringToDouble(this.raio);

    return Math.PI * Math.pow(raio, 2);
  }

  @Override
  public final Map<String, String> getMap() {
    return Collections.unmodifiableMap(
        Map.of(GEOMETRIA, geometria, ESTRUTURA, estrutura, RAIO, raio));
  }

  public static Disco from(Map<String, String> map) {
    final var materialStr = map.getOrDefault(MATERIAL, EMPTY);
    final var material = Material.from(materialStr);

    if (material.isRight()) throw Notification.with(material.getRight());

    return new Disco(
        map.getOrDefault(GEOMETRIA, EMPTY),
        map.getOrDefault(ESTRUTURA, EMPTY),
        material.getLeft(),
        map.getOrDefault(RAIO, EMPTY));
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
