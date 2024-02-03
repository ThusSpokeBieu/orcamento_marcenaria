package github.gmess.models;

import github.gmess.models.geometrias.Geometria;

/**
 * EstruturaValor
 */
public record EstruturaValor(
  String estrutura,
  String geometria,
  String valor
) {

  public static EstruturaValor from(Geometria geometria, String valor) {
    return new EstruturaValor(
      geometria.getEstrutura(), 
      geometria.getGeometria(), 
      valor);
  }
}
