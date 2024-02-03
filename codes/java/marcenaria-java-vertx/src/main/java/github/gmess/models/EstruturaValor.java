package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import github.gmess.models.geometrias.Geometria;

@CompiledJson
public record EstruturaValor(
  String estrutura,
  String geometria,
  String valor
) {

  public static EstruturaValor from(Geometria geometria, String valor) {
    return new EstruturaValor(
      geometria.getEstrutura(),
      geometria.getGeometria(),
      valor);
  }
}
