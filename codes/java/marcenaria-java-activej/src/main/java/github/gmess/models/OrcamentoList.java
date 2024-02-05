package github.gmess.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** OrcamentoList */
public record OrcamentoList(String id, double valorTotal, List<Orcamento> orcamentos) {

  public static OrcamentoList from(final MovelList moveis) {
    double precoTotal = 0;
    final List<Orcamento> orcamentos = new ArrayList<>();

    for (Movel movel : moveis.moveis()) {
      final var orcamento = Orcamento.from(movel);
      orcamentos.add(orcamento);
      precoTotal += orcamento.precoTotal();
    }

    return new OrcamentoList(UUID.randomUUID().toString(), precoTotal, orcamentos);
  }
}
