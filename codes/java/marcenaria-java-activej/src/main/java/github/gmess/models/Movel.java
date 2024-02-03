package github.gmess.models;

import java.util.Set;
import github.gmess.models.geometrias.Geometria;

public record Movel(
  String movel,
  Material material,
  Set<Geometria> geometrias) {}

