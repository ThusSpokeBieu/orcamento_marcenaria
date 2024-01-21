package github.com.marcenariaspring.models.geometrias;

public abstract class Geometria {
  protected final String estrutura;
  protected final String geometria;

  protected Geometria(
      final String estrutura,
      final String geometria) {
    this.estrutura = estrutura;
    this.geometria = geometria;
  }

  public abstract Double getArea();

}
