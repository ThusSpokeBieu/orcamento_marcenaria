package github.gmess.models;

import com.dslplatform.json.JsonValue;
import github.gmess.utils.StrUtils;

public enum Material {
  PINHO("pinho", 0.10),
  CARVALHO("carvalho", 0.30),
  EBANO("ebano", 5.0);

  private final String nome;

  private final double precoBase;

  @JsonValue
  public String getNomeAsString() {
    return nome;
  }

  public String getPrecoBasAsString() {
    return StrUtils.doubleToReal(precoBase);
  }

  public double getPrecoBase() {
    return precoBase;
  }

  Material(final String nome, final Double precoBase) {
    this.nome = nome;
    this.precoBase = precoBase;
  }

}
