package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Normalized;

public record Cubo(
    @Schema(description = "A estrutura da Geometria", example = "Estrutura Cubica")
        @JsonProperty("estrutura")
        String estrutura,
    @Schema(description = "Tipo da geometria", example = "Cubo") String geometria,
    @Schema(description = "Tamanho do cubo em centimetros", example = "1.0cm") double lado,
    @Normalized
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
  public static Cubo from(
      @JsonProperty("geometria") String geometria,
      @JsonProperty("estrutura") String estrutura,
      @JsonProperty("lado") String lado,
      @JsonProperty("material") Material material) {
    return new Cubo(estrutura, geometria, StrUtils.strToDouble(lado), material);
  }

  @JsonProperty("lado")
  public String getLadoAsString() {
    return StrUtils.doubleToStrCm(lado);
  }

  @Override
  public double getPreco() {
    return (Math.sqrt(3) * lado / 2) * material.getPrecoBase();
  }

  @Override
  public String getGeometria() {
    return "cubo";
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
