package github.gmess.moveis;

import github.gmess.geometrias.estruturas.Cilindro;
import github.gmess.geometrias.estruturas.Cubo;
import github.gmess.geometrias.estruturas.Esfera;
import github.gmess.geometrias.estruturas.Paralelepipedo;
import github.gmess.http.HttpError;
import github.gmess.utils.StringUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.Collections;

public class Movel extends JsonObject {

  private final String material;
  private final Double precoBase;
  private Double precoTotal = 0.0;
  private final String response;
  private final HttpError errors = HttpError.create();
  public Movel(final JsonObject request) {
    super(request == null ? Collections.emptyMap() : request.getMap());
    material = this.getString("material", "");
    precoBase = setPrecoBase();

    final StringBuffer sb = new StringBuffer();

    setEstruturas(sb, request.getJsonArray("geometrias"));

    sb.insert(0, STR."""
                     {
                       "movel": "\{request.getString("movel", "")}",
                       "material": "\{material}",
                       "preco_total": "\{StringUtils.doubleToRS(precoTotal)}",
                     """);

    response = sb.toString().trim();
  }

  public String getResponse() {
    return response;
  }

  public boolean isValid() {
    return errors.hasError();
  }

  public String getErr() {
    return errors.toString().trim();
  }

  public Double setPrecoBase() {
    switch(StringUtils.normalize(material).toLowerCase()) {
      case "pinho":     return 0.10;
      case "carvalho":  return 0.30;
      case "ebano":     return 5.00;

      default:          errors.initOrAdd(
        STR."O material \{material} é inválido. Opções válidas são: Pinho, Carvalho e Ébano"
                                        );
    }

    return 0.0;
  }

  public void setEstruturas(final StringBuffer sb, final JsonArray geometrias) {
    sb.append("[");

    for(int i = 0; i < geometrias.size(); i++) {
      var geometria = geometrias.getJsonObject(i);
      var tipo = geometria.getString("geometria", "");
      sb.append(STR."""
                    {
                      "estrutura": "\{geometria.getString("estrutura", "")}",
                      "geometria": "\{tipo}",
                      "preco_total": "\{getPrecoEstrutura(geometria, tipo)}"
                    }
                    """);
       if(!(i == geometrias.size() -1)) {
         sb.append(",");
       }
    }

    sb.append("]}");
  }


  public String getPrecoEstrutura(final JsonObject json, final String tipo) {
    final String estrutura = json.getString("estrutura", "estrutura");
    Double area;

    final var tipoNormalizado = StringUtils
      .normalize(tipo)
      .toLowerCase();

    try {
      switch (tipoNormalizado) {
        case "esfera":
          area = Esfera.calcular(json);
          break;

        case "cubo":
          area = Cubo.calcular(json);
          break;

        case "cilindro":
          area = Cilindro.calcular(json);
          break;

        case "paralelepipedo":
          area = Paralelepipedo.calcular(json);
          break;

        default: {
          errors.initOrAdd(
            STR."A estrutura \{estrutura} está com o tipo de geometria inválido: \{tipo}. Escolhe entre: esfera, cubo, cilindro ou paralelepipedo."
                          );
          area = 0.0;
          break;
        }
      }
    } catch (final Exception err) {
      err.printStackTrace();
      area = 0.0;
    }

    if(area > 0) {
      final var preco = area * precoBase;
      precoTotal += preco;
      return StringUtils.doubleToRS(preco);
    }

    errors.initOrAdd(
      STR."A estrutura \{estrutura} tem algum dado inválido, seus dados precisam ser em centímetros e acima de zero."
                    );

    return "";
  }
}
