package github.gmess.geometrias.estruturas;

import github.gmess.geometrias.Geometria;
import github.gmess.utils.StringUtils;
import io.vertx.core.json.JsonObject;

public class Cubo extends Geometria {

  public Cubo(
    final String estrutura,
    final Double lado,
    final Double precoBase) {
    nome = "Quadrado";
    area = (Math.sqrt(3) * lado) / 2;
    json = new JsonObject()
      .put("estrutura", estrutura)
      .put("geometria", nome)
      .put("area", area)
      .put("preco", StringUtils.doubleToRS(area * precoBase));
  }

  public static Cubo from(
    final JsonObject json,
    final Double precoBase) {
    return new Cubo(
      json.getString("estrutura", "quadrado"),
      json.getDouble("lado", 1.0),
      precoBase
    );
  }

  public static Double calcular(final JsonObject json) {
    final var ladoStr = StringUtils.onlyNumbers(json.getString("lado"));
    final var lado = Double.parseDouble(ladoStr);
    return (Math.sqrt(3) * lado) / 2;
  }



}