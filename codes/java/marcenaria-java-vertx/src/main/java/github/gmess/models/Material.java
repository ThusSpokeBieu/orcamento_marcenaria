package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import github.gmess.exceptions.Err;
import github.gmess.utils.StrUtils;
import io.vavr.control.Either;

@CompiledJson
public enum Material {
  PINHO("pinho", 0.10),
  COMPENSADO("compensado", 0.12),
  MDF("mdf", 0.15),
  FORMICA("formica", 0.18),
  FREIJO("freijó", 0.20),
  CEDRO("cedro", 0.25),
  CARVALHO("carvalho", 0.30),
  IPE("ipê", 0.45),
  MOGNO("mogno", 0.50),
  IMBUIA("imbuia", 0.55),
  JACARANDA("jacarandá", 0.60),
  ACO_INOX("aço inoxidável", 1.50),
  ACO_TEMPERADO("aço temperado", 2.00),
  EBANO("ébano", 5.0);

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

  public static Either<Material, Err> from(final String input) {
    final String normalized = StrUtils.normalizeString(input);

    return switch (normalized) {
      case "pinho" -> Either.left(Material.PINHO);
      case "compensado" -> Either.left(Material.COMPENSADO);
      case "mdf" -> Either.left(Material.MDF);
      case "formica" -> Either.left(Material.FORMICA);
      case "freijo" -> Either.left(Material.FREIJO);
      case "cedro" -> Either.left(Material.CEDRO);
      case "carvalho" -> Either.left(Material.CARVALHO);
      case "ipe" -> Either.left(Material.IPE);
      case "mogno" -> Either.left(Material.MOGNO);
      case "imbuia" -> Either.left(Material.IMBUIA);
      case "jacaranda" -> Either.left(Material.JACARANDA);
      case "acoinoxidavel" -> Either.left(Material.ACO_INOX);
      case "acotemperado" -> Either.left(Material.ACO_TEMPERADO);
      case "ebano" -> Either.left(Material.EBANO);
      default -> Either.right(new Err("Material inválido: " + input));
    };
  }
}
