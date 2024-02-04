package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public record Paralelepipedo(
    @Schema(description = "A estrutura da Geometria", example = "Estrutura de Paralelepipedo")
        @JsonProperty("estrutura")
        String estrutura,
    @Schema(description = "Tipo da geometria", example = "Paralelepipedo") String geometria,
    @Schema(description = "Tamanho da altura do paralelepipedo em centimetros", example = "1.0cm")
        double altura,
    @Schema(
            description = "Tamanho do comprimento do paralelepipedo em centimetros",
            example = "1.0cm")
        double comprimento,
    @Schema(description = "Tamanho da largura do paralelepipedo em centimetros", example = "1.0cm")
        double largura,
    @JsonProperty("material")
        @Schema(
            description = "Tipo do material",
            defaultValue = "pinho",
            enumAsRef = true,
            oneOf = Material.class,
            nullable = false)
        Material material)
    implements Geometria {

  @JsonCreator
  public static Paralelepipedo from(
      @JsonProperty("geometria") String geometria,
      @JsonProperty("estrutura") String estrutura,
      @JsonProperty("altura") String altura,
      @JsonProperty("comprimento") String comprimento,
      @JsonProperty("largura") String largura,
      @JsonProperty("material") Material material) {
    return new Paralelepipedo(
        estrutura,
        geometria,
        StrUtils.strToDouble(altura),
        StrUtils.strToDouble(comprimento),
        StrUtils.strToDouble(largura),
        material);
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
  public double getPreco() {
    return (2 * (comprimento * largura + comprimento * altura + largura * altura))
        * material.getPrecoBase();
  }

  @Override
  public String getGeometria() {
    return "paralelepipedo";
  }

  @Override
  public String getMaterial() {
    return material.getNomeAsString();
  }

  @Override
  public String getEstrutura() {
    return estrutura();
  }
}
