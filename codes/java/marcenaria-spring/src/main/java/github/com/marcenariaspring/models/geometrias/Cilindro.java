package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public record Cilindro(
    @Schema(description = "Tipo da geometria", example = "Cilindro") String geometria,
    @Schema(description = "Tamanho da altura do cilindro em centimetros", example = "1.0cm")
        double altura,
    @JsonProperty("material")
        @Schema(
            description = "Tipo do material",
            defaultValue = "pinho",
            enumAsRef = true,
            oneOf = Material.class,
            nullable = false)
        Material material,
    @Schema(description = "Tamanho do raio base do cilindro em centimetros", example = "1.0cm")
        @JsonProperty("raio_base")
        double raioBase,
    @Schema(description = "A estrutura da Geometria", example = "Estrutura Cilindrica")
        @JsonProperty("estrutura")
        String estrutura)
    implements Geometria {

  @JsonCreator
  public static Cilindro from(
      @JsonProperty("geometria") String geometria,
      @JsonProperty("estrutura") String estrutura,
      @JsonProperty("material") Material material,
      @JsonProperty("altura") String altura,
      @JsonProperty("raio_base") String raioBase) {

    return new Cilindro(
        geometria,
        StrUtils.strToDouble(altura),
        material,
        StrUtils.strToDouble(raioBase),
        estrutura);
  }

  @JsonProperty("altura")
  public String getAlturaAsString() {
    return StrUtils.doubleToStrCm(altura);
  }

  @JsonProperty("raio_base")
  public String getRaioBaseAsString() {
    return StrUtils.doubleToStrCm(raioBase);
  }

  @JsonProperty("estrutura")
  public String getEstrutura() {
    return estrutura();
  }

  public String getGeometria() {
    return geometria();
  }

  @Override
  public String getMaterial() {
    return material.getNomeAsString();
  }

  @Override
  public double getPreco() {
    return (2 * Math.PI * raioBase * (raioBase + altura)) * material().getPrecoBase();
  }
}
