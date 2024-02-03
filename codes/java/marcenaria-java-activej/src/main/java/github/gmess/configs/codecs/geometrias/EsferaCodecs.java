package github.gmess.configs.codecs.geometrias;

import github.gmess.models.geometrias.Esfera;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class EsferaCodecs {
  public static JsonCodec<Esfera> create() {
    return JsonCodecs.ofObject(
      Esfera::new,
      "geometria", Esfera::geometria, JsonCodecs.ofString(),
      "estrutura", Esfera::getEstrutura, JsonCodecs.ofString(),
      "raio", Esfera::raio, JsonCodecs.ofString()
    );
  }
}
