package github.com.marcenariaspring.models.moveis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.geometrias.Geometria;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EstruturaValor {

  @Schema(
    description = "O nome da estrutura",
    example = "perna dianteira esquerda")
  @JsonProperty("estrutura")
  private String estrutura;

  @Schema(description = "Tipo da geometria", example = "cilindro")
  @JsonProperty("geometria")
  private String geometria;

  @Schema(description = "Valor da estrutura", example = "R$50,00")
  @JsonIgnore
  private Double valor;

  private EstruturaValor(
    final String estrutura,
    final String geometria,
    final Double valor) {
    this.estrutura = estrutura;
    this.geometria = geometria;
    this.valor = valor;
  }

  public static EstruturaValor from(
    final Geometria geometria,
    final Material material) {
    return new EstruturaValor(
      geometria.getEstrutura(),
      geometria.getGeometria(),
      geometria.getPreco(material)
    );
  }

  @JsonProperty("valor")
  public String getValorAsStr() {
    return StrUtils.doubleToReal(valor);
  }
}
