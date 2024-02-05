package github.gmess.models;

import github.gmess.models.geometrias.Geometria;

/** EstruturaValor */
public record EstruturaValor(String geometria, String estrutura, String material, double valor) {

  public static EstruturaValor from(Geometria geometria) {
    return new EstruturaValor(
        geometria.getGeometria(),
        geometria.getEstrutura(),
        geometria.getMaterial(),
        geometria.getPreco());
  }
}
