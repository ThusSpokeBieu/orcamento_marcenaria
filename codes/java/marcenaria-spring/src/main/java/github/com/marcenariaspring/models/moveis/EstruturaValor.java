package github.com.marcenariaspring.models.moveis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public record EstruturaValor(
    @Schema(description = "O nome da estrutura", example = "perna dianteira esquerda")
        @JsonProperty("estrutura")
        String estrutura,
    @Schema(description = "Tipo da geometria", example = "cilindro") @JsonProperty("geometria")
        String geometria,
    @Schema(description = "Material da estrutura", example = "Carvalho") @JsonProperty("material")
        String material,
    @JsonIgnore Double valor) {

  public static EstruturaValor from(final Geometria geometria) {
    return new EstruturaValor(
        geometria.getEstrutura(),
        geometria.getGeometria(),
        geometria.getMaterial(),
        geometria.getPreco());
  }

  @Schema(description = "Valor da estrutura", example = "R$10,00")
  @JsonProperty("valor")
  public String getValorAsStr() {
    return StrUtils.doubleToReal(valor);
  }
}
