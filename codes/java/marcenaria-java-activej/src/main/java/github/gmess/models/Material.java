package github.gmess.models;

import static github.gmess.utils.StringConsts.ACO_INOX_NORMALIZED;
import static github.gmess.utils.StringConsts.ACO_TEMPERADO_NORMALIZED;
import static github.gmess.utils.StringConsts.CARVALHO_NORMALIZED;
import static github.gmess.utils.StringConsts.CEDRO_NORMALIZED;
import static github.gmess.utils.StringConsts.COMPENSADO_NORMALIZED;
import static github.gmess.utils.StringConsts.EBANO_NORMALIZED;
import static github.gmess.utils.StringConsts.FORMICA_NORMALIZED;
import static github.gmess.utils.StringConsts.FREIJO_NORMALIZED;
import static github.gmess.utils.StringConsts.IMBUIA_NORMALIZED;
import static github.gmess.utils.StringConsts.IPE_NORMALIZED;
import static github.gmess.utils.StringConsts.JACARANDA_NORMALIZED;
import static github.gmess.utils.StringConsts.MDF_NORMALIZED;
import static github.gmess.utils.StringConsts.MOGNO_NORMALIZED;
import static github.gmess.utils.StringConsts.PINHO_NORMALIZED;

import github.gmess.exceptions.Err;
import github.gmess.utils.StrUtils;
import io.activej.common.collection.Either;

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
  IMBUIA("imbuiá", 0.55),
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
      case PINHO_NORMALIZED -> Either.left(Material.PINHO);
      case COMPENSADO_NORMALIZED -> Either.left(Material.COMPENSADO);
      case MDF_NORMALIZED -> Either.left(Material.MDF);
      case FORMICA_NORMALIZED -> Either.left(Material.FORMICA);
      case FREIJO_NORMALIZED -> Either.left(Material.FREIJO);
      case CEDRO_NORMALIZED -> Either.left(Material.CEDRO);
      case CARVALHO_NORMALIZED -> Either.left(Material.CARVALHO);
      case IPE_NORMALIZED -> Either.left(Material.IPE);
      case MOGNO_NORMALIZED -> Either.left(Material.MOGNO);
      case IMBUIA_NORMALIZED -> Either.left(Material.IMBUIA);
      case JACARANDA_NORMALIZED -> Either.left(Material.JACARANDA);
      case ACO_INOX_NORMALIZED -> Either.left(Material.ACO_INOX);
      case ACO_TEMPERADO_NORMALIZED -> Either.left(Material.ACO_TEMPERADO);
      case EBANO_NORMALIZED -> Either.left(Material.EBANO);
      default -> Either.right(new Err("Material inválido: " + input));
    };
  }
}
