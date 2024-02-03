package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Esfera extends Geometria {

  @Schema(
    description = "A estrutura da Geometria",
    example = "Estrutura Esférica")
  @JsonProperty("estrutura")
  private String estrutura = "Estrutura Esférica";

  @Schema(description = "Tipo da geometria", example = "Esfera")
  private String geometria = "Esfera";

  @Schema(description = "Tamanho do raio em centimetros da esfera", example = "1.0cm")
  private Double raio;

  @JsonCreator
  public Esfera(
    @JsonProperty("estrutura") String estrutura,
    @JsonProperty("raio") String raio) {
    this.estrutura = estrutura;
    this.raio = StrUtils.strToDouble(raio);
  }

  @JsonProperty("raio")
  public String getRaioAsString() {
    return StrUtils.doubleToStrCm(raio);
  }

  @Override
  public Double getPreco(Material material) {
    return (4 * Math.PI * Math.pow(raio, 2)) * material.getPrecoBase();
  }
}
