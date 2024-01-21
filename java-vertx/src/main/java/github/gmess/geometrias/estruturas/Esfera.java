package github.gmess.geometrias.estruturas;

import github.gmess.geometrias.Geometria;
import github.gmess.utils.StringUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

public class Esfera extends Geometria {
  public Esfera(
    final String estrutura,
    final Double raio,
    final Double precoBase) {
    nome = "Esfera";
    area = 4 * Math.PI * Math.pow(raio, 2);
    json = new JsonObject()
      .put("estrutura", estrutura)
      .put("geometria", nome)
      .put("area", area)
      .put("preco", StringUtils.doubleToRS(area * precoBase));
  }

  public static Esfera from(
    final JsonObject json,
    final Double precoBase) {
    return new Esfera(
       json.getString("estrutura", "esfera"),
       json.getDouble("raio", 1.0),
       precoBase
    );
  }

  public static Double calcular(final JsonObject json) {
    final var raioStr = StringUtils.onlyNumbers(json.getString("raio"));
    final var raio = Double.parseDouble(raioStr);
    return 4 * Math.PI * Math.pow(raio, 2);
  }

}
