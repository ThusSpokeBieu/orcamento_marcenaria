package github.gmess.configs.codecs;

import github.gmess.models.EstruturaValor;
import github.gmess.models.Material;
import github.gmess.models.Orcamento;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class OrcamentoCodecs {
  public static final JsonCodec<Orcamento> create(
    JsonCodec<EstruturaValor> estruturaValorCodec
  ) {
    return JsonCodecs.ofObject(
      Orcamento::new, 
      "movel", Orcamento::movel, JsonCodecs.ofString(),
      "material", Orcamento::material, JsonCodecs.ofEnum(Material.class), 
      "preco_total", Orcamento::precoTotal, JsonCodecs.ofString(), 
      "estruturas", Orcamento::estruturas, JsonCodecs.ofSet(estruturaValorCodec)
    );
  }
}
