package github.gmess.configs.codecs.geometrias;

import github.gmess.models.geometrias.Cilindro;
import github.gmess.models.geometrias.Cubo;
import github.gmess.models.geometrias.Esfera;
import github.gmess.models.geometrias.Geometria;
import github.gmess.models.geometrias.Paralelepipedo;
import io.activej.json.JsonCodec;
import io.activej.json.SubclassJsonCodec;

public abstract class GeometriaCodecs {
 
  public static SubclassJsonCodec<Geometria> create(
    final JsonCodec<Esfera> esferaJsonCodec,
    final JsonCodec<Cubo> cuboJsonCodec,
    final JsonCodec<Cilindro> cilindroJsonCodec,
    final JsonCodec<Paralelepipedo> paralelepipedoJsonCodec
  ) {
    final SubclassJsonCodec<Geometria>.Builder builder = SubclassJsonCodec.<Geometria>builder();
    builder.with(Esfera.class, "esfera", esferaJsonCodec);
    builder.with(Cubo.class, "cubo", cuboJsonCodec);
    builder.with(Cilindro.class, "cilindro", cilindroJsonCodec);
    builder.with(Paralelepipedo.class, "paralelepipedo", paralelepipedoJsonCodec);
    return builder.build();
  }  
}
