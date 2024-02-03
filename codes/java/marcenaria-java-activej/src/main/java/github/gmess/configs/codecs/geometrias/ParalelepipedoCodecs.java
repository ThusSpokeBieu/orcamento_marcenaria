package github.gmess.configs.codecs.geometrias;

import github.gmess.models.geometrias.Paralelepipedo;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public class ParalelepipedoCodecs {
  public static JsonCodec<Paralelepipedo> create() {
    return JsonCodecs.ofObject(Paralelepipedo::new, 
      "geometria", Paralelepipedo::geometria, JsonCodecs.ofString(),
      "estrutura", Paralelepipedo::getEstrutura, JsonCodecs.ofString(), 
      "altura", Paralelepipedo::altura, JsonCodecs.ofString(),
      "comprimento",Paralelepipedo::comprimento, JsonCodecs.ofString(),
      "largura", Paralelepipedo::largura, JsonCodecs.ofString()
    );
  }
} 
