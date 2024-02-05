package github.gmess.configs.codecs;

import static github.gmess.utils.SerializerUtils.stringToDouble;
import static github.gmess.utils.SerializerUtils.toReal;

import github.gmess.models.EstruturaValor;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class EstruturaValorCodecs {
  public static JsonCodec<EstruturaValor> create() {
    return JsonCodecs.ofObject(
        EstruturaValor::new,
        "geometria",
        EstruturaValor::geometria,
        JsonCodecs.ofString(),
        "estrutura",
        EstruturaValor::estrutura,
        JsonCodecs.ofString(),
        "material",
        EstruturaValor::material,
        JsonCodecs.ofString(),
        "valor",
        EstruturaValor::valor,
        JsonCodecs.ofString().transform(toReal, stringToDouble));
  }
}
