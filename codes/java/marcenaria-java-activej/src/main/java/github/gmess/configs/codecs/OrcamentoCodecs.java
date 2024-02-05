package github.gmess.configs.codecs;

import static github.gmess.utils.SerializerUtils.stringToDouble;
import static github.gmess.utils.SerializerUtils.toReal;

import github.gmess.models.EstruturaValor;
import github.gmess.models.Orcamento;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class OrcamentoCodecs {
  public static final JsonCodec<Orcamento> create(JsonCodec<EstruturaValor> estruturaValorCodec) {
    return JsonCodecs.ofObject(
        Orcamento::new,
        "movel",
        Orcamento::movel,
        JsonCodecs.ofString(),
        "preco_total",
        Orcamento::precoTotal,
        JsonCodecs.ofString().transform(toReal, stringToDouble),
        "estruturas",
        Orcamento::estruturas,
        JsonCodecs.ofList(estruturaValorCodec));
  }
}
