package github.gmess.models;

import github.gmess.models.geometrias.Geometria;
import java.util.ArrayList;
import java.util.List;

public record Orcamento(String movel, double precoTotal, List<EstruturaValor> estruturas) {

  public static Orcamento from(final Movel movel) {
    double precoFinal = 0;
    final List<EstruturaValor> estruturas = new ArrayList<>();

    for (Geometria geometria : movel.geometrias()) {
      estruturas.add(EstruturaValor.from(geometria));
      precoFinal += geometria.getPreco();
    }

    return new Orcamento(movel.movel(), precoFinal, estruturas);
  }
}
