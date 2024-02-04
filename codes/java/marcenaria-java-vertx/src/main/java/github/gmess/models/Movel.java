package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import github.gmess.models.geometrias.Geometria;

@CompiledJson
public record Movel(String movel, Material material, Geometria[] geometrias) {
}
