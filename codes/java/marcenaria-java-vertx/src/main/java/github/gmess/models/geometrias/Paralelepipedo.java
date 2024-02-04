package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.Material;
import github.gmess.utils.MaterialSerializer;
import github.gmess.utils.NumberUtils;

@CompiledJson(name = "paralelepipedo")
public record Paralelepipedo(
    @JsonAttribute(name = "estrutura") String estruturaParalelepipedo,
    @JsonAttribute(converter = MaterialSerializer.class) Material material,
    String altura,
    String comprimento,
    String largura)
    implements Geometria {
  @Override
  public final String getGeometria() {
    return "cilindro";
  }

  @Override
  public final String getEstrutura() {
    return this.estruturaParalelepipedo();
  }

  @Override
  public final double getArea() {
    final double altura = NumberUtils.centimetersToDouble(this.largura);
    final double comprimento = NumberUtils.centimetersToDouble(this.comprimento);
    final double largura = NumberUtils.centimetersToDouble(this.altura);

    return 2 * (comprimento * largura + comprimento * altura + largura * altura);
  }

  @Override
  public double getPreco() {
    return getArea() * material.getPrecoBase();
  }
  ;

  @Override
  public String getMaterial() {
    return material.getNomeAsString();
  }
}
