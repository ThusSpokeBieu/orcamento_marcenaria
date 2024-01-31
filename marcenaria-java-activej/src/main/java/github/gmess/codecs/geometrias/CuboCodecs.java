package github.gmess.codecs.geometrias;

import github.gmess.models.geometrias.Cubo;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class CuboCodecs {
  public static JsonCodec<Cubo> create() {
    return JsonCodecs.ofObject(
      Cubo::new,
      "geometria", Cubo::geometria, JsonCodecs.ofString(),
      "estrutura", Cubo::getEstrutura, JsonCodecs.ofString(),
      "lado", Cubo::lado, JsonCodecs.ofString()
    );
  }
}
