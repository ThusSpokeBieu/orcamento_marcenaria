package github.gmess.models.geometrias;

import java.util.Map;

public interface Geometria {
  String getEstrutura();

  String getGeometria();

  double getArea();

  double getPreco();

  String getMaterial();

  Map<String, String> getMap();
}
