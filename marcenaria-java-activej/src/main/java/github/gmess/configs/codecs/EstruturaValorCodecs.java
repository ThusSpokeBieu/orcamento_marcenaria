package github.gmess.configs.codecs;

import github.gmess.models.EstruturaValor;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class EstruturaValorCodecs {
  public static JsonCodec<EstruturaValor> create() {
    return JsonCodecs.ofObject(EstruturaValor::new, 
      "estrutura", EstruturaValor::estrutura, JsonCodecs.ofString(),
      "geometria", EstruturaValor::geometria, JsonCodecs.ofString(),
      "valor", EstruturaValor::valor, JsonCodecs.ofString());
  }
}
