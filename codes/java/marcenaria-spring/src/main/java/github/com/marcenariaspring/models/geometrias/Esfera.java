package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public record Esfera(
    @Schema(description = "A estrutura da Geometria", example = "Estrutura Esférica")
        @JsonProperty("estrutura")
        String estrutura,
    @Schema(description = "Tipo da geometria", example = "Esfera") String geometria,
    @Schema(description = "Tamanho do raio em centimetros da esfera", example = "1.0cm")
        double raio,
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
  public static Esfera from(
      @JsonProperty("geometria") String geometria,
      @JsonProperty("estrutura") String estrutura,
      @JsonProperty("raio") String raio,
      @JsonProperty("material") Material material) {
    return new Esfera(estrutura, geometria, StrUtils.strToDouble(raio), material);
  }

  @JsonProperty("raio")
  public String getRaioAsString() {
    return StrUtils.doubleToStrCm(raio);
  }

  @Override
  public double getPreco() {
    return (4 * Math.PI * Math.pow(raio, 2)) * material.getPrecoBase();
  }

  @Override
  public String getGeometria() {
    return "esfera";
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
