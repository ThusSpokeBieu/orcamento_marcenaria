package github.gmess.configs.codecs.geometrias;

import static github.gmess.utils.StringConsts.CILINDRO;
import static github.gmess.utils.StringConsts.CONE;
import static github.gmess.utils.StringConsts.CUBO;
import static github.gmess.utils.StringConsts.DISCO;
import static github.gmess.utils.StringConsts.ESFERA;
import static github.gmess.utils.StringConsts.GEOMETRIA;
import static github.gmess.utils.StringConsts.PARALELEPIPEDO_NORMALIZED;
import static github.gmess.utils.StringConsts.PIRAMIDE_NORMALIZED;

import github.gmess.models.geometrias.Cilindro;
import github.gmess.models.geometrias.Cone;
import github.gmess.models.geometrias.Cubo;
import github.gmess.models.geometrias.Disco;
import github.gmess.models.geometrias.Esfera;
import github.gmess.models.geometrias.Geometria;
import github.gmess.models.geometrias.Paralelepipedo;
import github.gmess.models.geometrias.Piramide;
import github.gmess.utils.StrUtils;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;
import io.activej.json.JsonFunction;
import io.activej.json.JsonKeyCodec;
import io.activej.json.JsonValidationException;
import java.util.Map;
import java.util.function.Function;

public abstract class GeometriaCodecs {

  public static JsonCodec<Geometria> create() {
    return JsonCodecs.ofMap(JsonKeyCodec.ofStringKey(), JsonCodecs.ofString()).transform(to, from);
  }

  private static Function<Geometria, Map<String, String>> to = geometria -> geometria.getMap();

  private static JsonFunction<Map<String, String>, Geometria> from =
      map -> {
        final var geometria = map.getOrDefault(GEOMETRIA, "");

        return switch (StrUtils.normalizeString(geometria)) {
          case ESFERA -> Esfera.from(map);
          case CUBO -> Cubo.from(map);
          case CILINDRO -> Cilindro.from(map);
          case PARALELEPIPEDO_NORMALIZED -> Paralelepipedo.from(map);
          case PIRAMIDE_NORMALIZED -> Piramide.from(map);
          case DISCO -> Disco.from(map);
          case CONE -> Cone.from(map);
          default ->
              throw new JsonValidationException("Geometria inválida ou inexistente: " + geometria);
        };
      };
}
