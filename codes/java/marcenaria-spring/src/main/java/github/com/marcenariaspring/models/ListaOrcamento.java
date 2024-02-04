package github.com.marcenariaspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.moveis.Movel;
import github.com.marcenariaspring.models.moveis.Orcamento;
import github.com.marcenariaspring.utils.StrUtils;
import java.util.UUID;

public record ListaOrcamento(UUID id, @JsonIgnore double precoTotal, Orcamento[] orcamentos) {

  public static ListaOrcamento from(ListaMoveis moveis) {
    int i = 0;
    double valorTotal = 0;
    Orcamento[] orcamentos = new Orcamento[moveis.moveis().length];

    for (Movel movel : moveis.moveis()) {
      orcamentos[i] = Orcamento.from(movel);
      valorTotal += orcamentos[i].precoTotal();
      i++;
    }

    return new ListaOrcamento(UUID.randomUUID(), valorTotal, orcamentos);
  }

  @JsonProperty("valor_total")
  public String getValorTotal() {
    return StrUtils.doubleToReal(precoTotal);
  }
}
