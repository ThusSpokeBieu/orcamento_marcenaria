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
public class Paralelepipedo extends Geometria {


  @Schema(
    description = "A estrutura da Geometria",
    example = "Estrutura de Paralelepipedo")
  @JsonProperty("estrutura")
  private String estrutura = "Estrutura de Paralelepipedo";
  @Schema(description = "Tipo da geometria", example = "Paralelepipedo")
  private final String geometria = "Paralelepipedo";
  @Schema(description = "Tamanho da altura do paralelepipedo em centimetros", example = "1.0cm")
  private Double altura;
  @Schema(description = "Tamanho do comprimento do paralelepipedo em centimetros", example = "1.0cm")
  private Double comprimento;
  @Schema(description = "Tamanho da largura do paralelepipedo em centimetros", example = "1.0cm")
  private Double largura;

  @JsonCreator
  public Paralelepipedo(
    @JsonProperty("estrutura") String estrutura,
    @JsonProperty("altura") String altura,
    @JsonProperty("comprimento") String comprimento,
    @JsonProperty("largura") String largura) {
    this.estrutura = estrutura;
    this.altura = StrUtils.strToDouble(altura);
    this.comprimento = StrUtils.strToDouble(comprimento);
    this.largura = StrUtils.strToDouble(largura);
  }

  @JsonProperty("altura")
  public String getAlturaAsString() {
    return StrUtils.doubleToStrCm(altura);
  }

  @JsonProperty("comprimento")
  public String getComprimentoAsString() {
    return StrUtils.doubleToStrCm(comprimento);
  }

  @JsonProperty("largura")
  public String getLarguraAsString() {
    return StrUtils.doubleToStrCm(largura);
  }

  @Override
  public Double getPreco(Material material) {
    return (2 * (comprimento * largura + comprimento * altura + largura * altura))
      * material.getPrecoBase();
  }
}
