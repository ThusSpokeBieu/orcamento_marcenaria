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
public class Cilindro extends Geometria {

  @Schema(description = "Tipo da geometria", example = "Cilindro")
  private final String geometria = "Cilindro";

  @Schema(description = "Tamanho da altura do cilindro em centimetros", example = "1.0cm")
  private Double altura;

  @Schema(
    description = "Tamanho do raio base do cilindro em centimetros",
    example = "1.0cm")
  @JsonProperty("raio_base")
  private Double raioBase;

  @Schema(
    description = "A estrutura da Geometria",
    example = "Estrutura Cilindrica")
  @JsonProperty("estrutura")
  private String estrutura = "Estrutura Cilindrica";

  @JsonCreator
  public Cilindro(
      @JsonProperty("estrutura") String estrutura,
      @JsonProperty("altura") String altura,
      @JsonProperty("raio_base") String raioBase) {
    this.estrutura = estrutura;
    this.altura = StrUtils.strToDouble(altura);
    this.raioBase = StrUtils.strToDouble(raioBase);
  }

  @JsonProperty("altura")
  public String getAlturaAsString() {
    return StrUtils.doubleToStrCm(altura);
  }

  @JsonProperty("raio_base")
  public String getRaioBaseAsString() {
    return StrUtils.doubleToStrCm(raioBase);
  }

  @Override
  public Double getPreco(Material material) {
    return (2 * Math.PI * raioBase * (raioBase + altura)) * material.getPrecoBase();
  }
}
