package github.com.marcenariaspring.models.moveis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public record Orcamento(
    @JsonProperty("movel") @Schema(description = "Nome do móvel", example = "Cadeira") String movel,
    @JsonIgnore Double precoTotal,
    @Schema(description = "Estruturas e seus valores") @JsonProperty("estruturas")
        EstruturaValor[] estruturas) {

  @JsonProperty("preco_total")
  @Schema(description = "Soma dos valores das estruturas", example = "R$50,00")
  public String getPrecoTotalAsStr() {
    return StrUtils.doubleToReal(precoTotal);
  }

  public static Orcamento from(final Movel movel) {
    final var tamanho = movel.geometrias().length;
    final var estruturas = new EstruturaValor[tamanho];
    double valorTotal = 0;
    int i = 0;

    for (Geometria geometria : movel.geometrias()) {
      estruturas[i] = EstruturaValor.from(geometria);
      valorTotal += geometria.getPreco();
      i++;
    }

    return new Orcamento(movel.movel(), valorTotal, estruturas);
  }
}
