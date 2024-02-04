package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.utils.StrUtils;
import java.util.UUID;

@CompiledJson
public record OrcamentoList(
    UUID id, @JsonAttribute(name = "preco_total") String precoTotal, Orcamento[] orcamentos) {
  public static OrcamentoList from(MovelList moveis) {
    int i = 0;
    int tamanho = moveis.moveis().length;
    double precoTotal = 0;
    final Orcamento[] orcamentos = new Orcamento[tamanho];

    for (Movel movel : moveis.moveis()) {
      orcamentos[i] = Orcamento.from(movel);
      precoTotal += orcamentos[i].precoTotal();
      i++;
    }

    return new OrcamentoList(UUID.randomUUID(), StrUtils.doubleToReal(precoTotal), orcamentos);
  }
}
