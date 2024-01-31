package github.gmess.models.geometrias;

import java.util.HashSet;
import java.util.Set;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;
import io.activej.json.JsonUtils;

public class GeometriaConst {
  public final Set<Geometria> GEOMETRIAS = new HashSet<Geometria>();

  public final byte[] BYTES;
  public final String JSON;

  public GeometriaConst(final JsonCodec<Geometria> jsonCodec) {

    final var setCodec = JsonCodecs.ofSet(jsonCodec);
    
    final var esfera = new Esfera("esfera", "Estrutura Esférica", "1,0cm");
    final var cubo = new Cubo("cubo", "Estrutura Cúbica", "1,0cm");
    final var cilindro = new Cilindro("cilindro", "Estrutura Cilindríca", "1,0cm", "1,0cm");
    final var paralelepipedo = new Paralelepipedo("paralelepipedo", "Estrutura Paralelepipedo", "1,0cm", "1,0cm", "1,0cm");

    GEOMETRIAS.add(esfera);
    GEOMETRIAS.add(cubo);
    GEOMETRIAS.add(cilindro);
    GEOMETRIAS.add(paralelepipedo);

    JSON = JsonUtils.toJson(setCodec, this.GEOMETRIAS);
    BYTES = JsonUtils.toJsonBytes(setCodec, this.GEOMETRIAS);
  }
}
