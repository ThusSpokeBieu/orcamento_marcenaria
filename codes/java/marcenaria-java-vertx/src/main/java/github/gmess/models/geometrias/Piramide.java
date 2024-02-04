package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.Material;
import github.gmess.utils.MaterialSerializer;
import github.gmess.utils.NumberUtils;

/** PiramideBaseQuadrada */
@CompiledJson(name = "piramide")
public record Piramide(
    @JsonAttribute(name = "estrutura") String estruturaPiramide,
    @JsonAttribute(converter = MaterialSerializer.class) Material material,
    String lado,
    String altura)
    implements Geometria {

  @Override
  public String getEstrutura() {
    return estruturaPiramide();
  }

  @Override
  public String getGeometria() {
    return "piramide";
  }

  @Override
  public String getMaterial() {
    return material.getNomeAsString();
  }

  @Override
  public double getArea() {
    final double lado = NumberUtils.centimetersToDouble(this.lado);
    final double altura = NumberUtils.centimetersToDouble(this.altura);

    return Math.pow(lado, 2) + 4 * (lado * altura / 2);
  }

  @Override
  public double getPreco() {
    return getArea() * material.getPrecoBase();
  }
}
