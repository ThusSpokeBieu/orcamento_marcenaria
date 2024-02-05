package github.gmess.configs.codecs;

import github.gmess.models.Orcamento;
import github.gmess.models.OrcamentoList;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class OrcamentoListCodecs {
  public static JsonCodec<OrcamentoList> create(final JsonCodec<Orcamento> orcamentoJsonCodec) {
    return JsonCodecs.ofObject(
        OrcamentoList::new,
        "id",
        OrcamentoList::id,
        JsonCodecs.ofString(),
        "valor_total",
        OrcamentoList::valorTotal,
        JsonCodecs.ofDouble(),
        "orcamentos",
        OrcamentoList::orcamentos,
        JsonCodecs.ofList(orcamentoJsonCodec));
  }
}
