package github.gmess.models;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import github.gmess.models.geometrias.Geometria;

@CompiledJson
public record Movel(
  String movel,
  Material material,
  @JsonAttribute(typeSignature = CompiledJson.TypeSignature.EXCLUDE) 
  Geometria[] geometrias) {}

