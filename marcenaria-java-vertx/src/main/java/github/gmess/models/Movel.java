package github.gmess.models;

import java.util.Set;
import com.dslplatform.json.CompiledJson;
import github.gmess.models.geometrias.Geometria;

@CompiledJson
public record Movel(
  String movel,
  Material material,
  Set<Geometria> geometrias) {}

