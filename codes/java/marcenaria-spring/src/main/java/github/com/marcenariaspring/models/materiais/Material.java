package github.com.marcenariaspring.models.materiais;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.com.marcenariaspring.utils.StrUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ValidationException;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING)
@Schema(allOf = Material.class)
public enum Material {
  PINHO("pinho", 0.10),
  CARVALHO("carvalho", 0.30),
  EBANO("ebano", 5.0);

  private final String nome;
  @JsonIgnore private final Double precoBase;

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

  @JsonCreator
  public static Material from(final String str) {
    final var normalized = StrUtils.normalizeString(str);

    for (Material material : Material.values()) {
      if (material.nome.equalsIgnoreCase(normalized)) return material;
    }

    throw new ValidationException("O valor " + str + "não é um material válido.");
  }
}
