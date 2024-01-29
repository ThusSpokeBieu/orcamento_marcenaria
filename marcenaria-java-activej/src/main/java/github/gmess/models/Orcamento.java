package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.utils.StrUtils;

/**
 * Orcamento
 */
@CompiledJson
public record Orcamento(
  String movel,
  Material material,
  @JsonAttribute(name = "preço_total")
  String precoTotal,
  EstruturaValor[] estruturas
) {

  public static Orcamento from(final Movel movel) {
    double precoFinal = 0;
    final var geometrias = movel.geometrias();
    final var material = movel.material();
    final var estruturas = new EstruturaValor[geometrias.length];

    for(int i = 0; i < geometrias.length; i++) {
      final double precoGeometria = geometrias[i].getArea() * material.getPrecoBase();
      precoFinal += precoGeometria;

      estruturas[i] = EstruturaValor.from(geometrias[i], StrUtils.doubleToReal(precoGeometria));
    }

    return new Orcamento(
      movel.movel(),
      movel.material(),
      StrUtils.doubleToReal(precoFinal),
      estruturas
    );
  }
}
