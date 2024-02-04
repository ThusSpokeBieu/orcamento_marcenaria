package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import github.gmess.models.geometrias.Geometria;
import github.gmess.utils.StrUtils;

@CompiledJson
public record EstruturaValor(String estrutura, String geometria, String material, String valor) {

  public static EstruturaValor from(Geometria geometria) {
    return new EstruturaValor(
        geometria.getEstrutura(),
        geometria.getGeometria(),
        geometria.getMaterial(),
        StrUtils.doubleToReal(geometria.getPreco()));
  }
}
