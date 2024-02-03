package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import github.gmess.utils.StrUtils;

@CompiledJson
public enum Material {
  pinho("pinho", 0.10),
  carvalho("carvalho", 0.30),
  ebano("ebano", 5.0);

  private final String nome;

  private final double precoBase;

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

  public static boolean validate(final String input) {
    final String normalized = StrUtils.normalizeString(input);

    for (Material material : Material.values()) {
      if (material.getNomeAsString().equals(normalized)) {
        return true;
      }
    }

    return false;
  }


}
