package github.gmess.geometrias.estruturas;

import github.gmess.exceptions.errors.Error;
import github.gmess.geometrias.Geometria;
import github.gmess.utils.StringUtils;
import github.gmess.utils.validators.Validator;
import io.vertx.core.json.JsonObject;
import java.util.HashSet;

public class Paralelepipedo
  extends Geometria {

  public Paralelepipedo(
    final String estrutura,
    final Double altura,
    final Double comprimento,
    final Double largura,
    final Double precoBase) {
    nome = "Paralepipedo";
    area = 2 * (comprimento * largura + comprimento * altura + largura * altura);
    json = new JsonObject()
      .put("estrutura", estrutura)
      .put("geometria", nome)
      .put("area", area)
      .put("preco", StringUtils.doubleToRS(area * precoBase));
  }

  public static Paralelepipedo from(
    final JsonObject json,
    final Double precoBase) {
    final var errors = new HashSet<Error>();

    final var estrutura = json.getString("estrutura", "");
    final var altura = json.getString("altura", "");
    final var comprimento = json.getString("comprimento", "");
    final var largura =  json.getString("largura", "");

    return new Paralelepipedo(
      estrutura,
      Validator.validateDouble(Paralelepipedo.class, "altura", altura, errors),
      Validator.validateDouble(Paralelepipedo.class, "comprimento", comprimento, errors),
      Validator.validateDouble(Paralelepipedo.class, "largura", largura, errors),
      precoBase
    );
  }

  public static Double calcular(final JsonObject json) {
    final var alturaStr = StringUtils.onlyNumbers(json.getString("altura"));
    final var comprimentoStr = StringUtils.onlyNumbers(json.getString("comprimento"));
    final var larguraStr =  StringUtils.onlyNumbers(json.getString("largura"));

    final var altura = Double.parseDouble(alturaStr);
    final var comprimento = Double.parseDouble(comprimentoStr);
    final var largura =  Double.parseDouble(larguraStr);

    return 2 * (comprimento * largura + comprimento * altura + largura * altura);
  }

}