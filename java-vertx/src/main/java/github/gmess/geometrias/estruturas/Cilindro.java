package github.gmess.geometrias.estruturas;

import github.gmess.geometrias.Geometria;
import github.gmess.utils.StringUtils;
import io.vertx.core.json.JsonObject;

public class Cilindro extends Geometria {

  public Cilindro(
    final String estrutura,
    final Double altura,
    final Double raioBase,
    final Double precoBase) {
    nome = "Cilindro";
    area = 2 * Math.PI * raioBase * (raioBase + altura);
    json = new JsonObject()
      .put("estrutura", estrutura)
      .put("geometria", nome)
      .put("area", area)
      .put("preco", StringUtils.doubleToRS(area * precoBase));
  }

  public static Cilindro from(
    final JsonObject json,
    final Double precoBase) {
    return new Cilindro(
      json.getString("estrutura", "cilindro"),
      json.getDouble("altura", 1.0),
      json.getDouble("raio_base", 1.0),
      precoBase
    );
  }

  public static Double calcular(final JsonObject json) {
    final var raioBaseStr = StringUtils.onlyNumbers(json.getString("raio_base"));
    final var alturaStr = StringUtils.onlyNumbers(json.getString("altura"));
    final var raioBase =  Double.parseDouble(raioBaseStr);
    final var altura = Double.parseDouble(alturaStr);
    return 2 * Math.PI * raioBase * (raioBase + altura);
  }


}