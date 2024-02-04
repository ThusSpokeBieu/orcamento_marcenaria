package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.geometrias.Geometria;
import github.gmess.utils.NumberUtils;
import github.gmess.utils.StrUtils;

public record Orcamento(
    String movel,
    @JsonAttribute(name = "preco_total") String precoTotalStr,
    @JsonAttribute(ignore = true) double precoTotal,
    EstruturaValor[] estruturas) {

  @CompiledJson
  public static Orcamento from(
      String movel,
      @JsonAttribute(name = "preco_total") String precoTotalStr,
      EstruturaValor[] estruturas) {
    return new Orcamento(
        movel, precoTotalStr, NumberUtils.centimetersToDouble(precoTotalStr), estruturas);
  }

  public static Orcamento from(final Movel movel) {
    double precoFinal = 0;
    int i = 0;
    int length = movel.geometrias().length;
    final EstruturaValor[] estruturas = new EstruturaValor[length];

    for (Geometria geometria : movel.geometrias()) {
      estruturas[i] = EstruturaValor.from(geometria);
      precoFinal += geometria.getPreco();
      i++;
    }

    return new Orcamento(movel.movel(), StrUtils.doubleToReal(precoFinal), precoFinal, estruturas);
  }
}
