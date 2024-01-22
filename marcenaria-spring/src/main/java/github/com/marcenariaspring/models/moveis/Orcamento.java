package github.com.marcenariaspring.models.moveis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.models.materiais.Material;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Orcamento {
  @JsonProperty("movel")
  @Schema(description = "Nome do móvel", example = "R$50,00")
  private String movel;
  @JsonProperty("material")
  @Schema(description = "Material do orçamento", example = "R$50,00")
  private String material;
  @JsonIgnore
  private Double precoTotal;

  @Schema(description = "Estruturas e seus valores")
  @JsonProperty("estruturas")
  private Set<EstruturaValor> estruturas;
  public Orcamento(
    final String movel,
    final String material,
    final Double precoTotal,
    final Set<EstruturaValor> estruturas) {
    this.movel = movel;
    this.material = material;
    this.precoTotal = precoTotal;
    this.estruturas = estruturas;
  }

  @JsonProperty("preco_total")
  @Schema(description = "Soma dos valores das estruturas", example = "R$50,00")
  public String getPrecoTotalAsStr() {
    return StrUtils.doubleToReal(precoTotal);
  }

  public static Orcamento from(final Movel movel) {
    final var materialStr = StrUtils.normalizeString(movel.getMaterial());
    final var material = Material.valueOf(materialStr);
    final var estruturas = Arrays
                            .stream(movel.getGeometrias())
                            .parallel()
                            .map(geometria -> EstruturaValor.from(geometria, material))
                            .collect(Collectors.toUnmodifiableSet());
    return new Orcamento(
      movel.getMovel(),
      movel.getMaterial(),
      estruturas.parallelStream().mapToDouble(EstruturaValor::getValor).sum(),
      estruturas
    );
  }
}
