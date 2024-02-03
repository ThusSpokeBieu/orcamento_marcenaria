package github.com.marcenariaspring.models.materiais;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Schema(
  allOf = Material.class )
public enum Material {
  PINHO("pinho", 0.10),
  CARVALHO("carvalho", 0.30),
  EBANO("ebano", 5.0);

  private final String nome;
  @JsonIgnore
  private final Double precoBase;

  @JsonProperty("nome")
  public String getNomeAsString() {
    return nome;
  }

  @JsonProperty("preco_base")
  public String getPrecoBasAsString() {
    return StrUtils.doubleToReal(precoBase);
  }

  Material(final String nome, final Double precoBase) {
    this.nome = nome;
    this.precoBase = precoBase;
  }

}
