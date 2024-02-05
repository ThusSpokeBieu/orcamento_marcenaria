package github.gmess.models;

import github.gmess.models.geometrias.Geometria;
import java.util.List;

public record Movel(String movel, List<Geometria> geometrias) {}
