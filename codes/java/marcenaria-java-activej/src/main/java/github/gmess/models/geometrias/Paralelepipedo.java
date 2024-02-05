package github.gmess.models.geometrias;

import static github.gmess.utils.StringConsts.ALTURA;
import static github.gmess.utils.StringConsts.COMPRIMENTO;
import static github.gmess.utils.StringConsts.EMPTY;
import static github.gmess.utils.StringConsts.ESTRUTURA;
import static github.gmess.utils.StringConsts.GEOMETRIA;
import static github.gmess.utils.StringConsts.LARGURA;
import static github.gmess.utils.StringConsts.MATERIAL;
import static github.gmess.utils.StringConsts.PARALELEPIPEDO;

import github.gmess.exceptions.Notification;
import github.gmess.models.Material;
import github.gmess.utils.NumberUtils;
import java.util.Collections;
import java.util.Map;

public record Paralelepipedo(
    String geometria,
    String estrutura,
    Material material,
    String altura,
    String comprimento,
    String largura)
    implements Geometria {
  @Override
  public final String getGeometria() {
    return PARALELEPIPEDO;
  }

  @Override
  public final String getEstrutura() {
    return this.estrutura;
  }

  @Override
  public final double getArea() {
    final double altura = NumberUtils.stringToDouble(this.largura);
    final double comprimento = NumberUtils.stringToDouble(this.comprimento);
    final double largura = NumberUtils.stringToDouble(this.altura);

    return 2 * (comprimento * largura + comprimento * altura + largura * altura);
  }

  @Override
  public final Map<String, String> getMap() {
    return Collections.unmodifiableMap(
        Map.of(
            GEOMETRIA,
            geometria,
            ESTRUTURA,
            estrutura,
            ALTURA,
            altura,
            COMPRIMENTO,
            comprimento,
            LARGURA,
            largura));
  }

  public static Paralelepipedo from(Map<String, String> map) {
    final var materialStr = map.getOrDefault(MATERIAL, EMPTY);
    final var material = Material.from(materialStr);

    if (material.isRight()) throw Notification.with(material.getRight());

    return new Paralelepipedo(
        map.getOrDefault(GEOMETRIA, EMPTY),
        map.getOrDefault(ESTRUTURA, EMPTY),
        material.getLeft(),
        map.getOrDefault(ALTURA, EMPTY),
        map.getOrDefault(COMPRIMENTO, EMPTY),
        map.getOrDefault(LARGURA, EMPTY));
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
