package github.gmess.configs.codecs;

import github.gmess.models.Material;
import github.gmess.models.Movel;
import github.gmess.models.geometrias.Geometria;
import io.activej.json.JsonCodec;
import io.activej.json.JsonCodecs;

public abstract class MovelCodecs {
  public static JsonCodec<Movel> create(
    final JsonCodec<Geometria> geometriaJsonCodec
  ) {
    return JsonCodecs.ofObject(
      Movel::new, 
      "movel", Movel::movel, JsonCodecs.ofString(), 
      "material", Movel::material, JsonCodecs.ofEnum(Material.class),
      "geometrias", Movel::geometrias, JsonCodecs.ofSet(geometriaJsonCodec)
    );
  }
}
