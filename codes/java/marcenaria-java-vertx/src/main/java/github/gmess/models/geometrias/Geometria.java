package github.gmess.models.geometrias;

import com.dslplatform.json.CompiledJson;

@CompiledJson(discriminator = "geometria")
public interface Geometria {
  String getEstrutura();

  String getGeometria();

  String getMaterial();

  double getArea();

  double getPreco();
}
