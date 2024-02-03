package github.gmess.configs.codecs.geometrias;

import github.gmess.models.geometrias.Cilindro;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class CilindroCodecs {
  public static JsonCodec<Cilindro> create() {
    return JsonCodecs.ofObject(
       Cilindro::new,
          "geometria", Cilindro::geometria, JsonCodecs.ofString(),
          "estrutura", Cilindro::getEstrutura, JsonCodecs.ofString(),
          "raio_base", Cilindro::raioBase, JsonCodecs.ofString(),
          "altura", Cilindro::altura, JsonCodecs.ofString()
       );
  }
}
