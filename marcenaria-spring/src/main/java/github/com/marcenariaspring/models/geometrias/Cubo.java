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
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cubo extends Geometria {

  @Schema(
    description = "A estrutura da Geometria",
    example = "Estrutura Cubica")
  @JsonProperty("estrutura")
  private String estrutura = "Estrutura Cubica";

  @Schema(description = "Tipo da geometria", example = "Cubo")
  private String geometria = "Cubo";

  @Schema(description = "Tamanho do cubo em centimetros", example = "1.0cm")
  private Double lado;

  @JsonCreator
  public Cubo(
    @JsonProperty("estrutura") String estrutura,
    @JsonProperty("lado") String lado) {
    this.estrutura = estrutura;
    this.lado = StrUtils.strToDouble(lado);
  }

  @JsonProperty("lado")
  public String getLadoAsString() {
    return StrUtils.doubleToStrCm(lado);
  }

  @Override
  public Double getPreco(final Material material) {
    return (Math.sqrt(3) * lado / 2) * material.getPrecoBase();
  }
}
