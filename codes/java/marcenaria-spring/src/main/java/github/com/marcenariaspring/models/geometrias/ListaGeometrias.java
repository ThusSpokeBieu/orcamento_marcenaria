package github.com.marcenariaspring.models.geometrias;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class ListaGeometrias {
  private static byte[] all;
  private static Geometria[] array;
  private static String json;

  @SneakyThrows
  public static byte[] instance() {
    if (all != null && array.length > 0) {
      return all;
    }

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();

    final var esfera = new Esfera("Estrutura Esférica", "1.0cm");
    final var cubo = new Cubo("Estrutura Cúbica", "1.0cm");
    final var cilindro = new Cilindro("Estrutura Cilindríca", "1.0cm", "1.0cm");
    final var paralelepipedo = new Paralelepipedo("Estrutura de Paralelepipedo", "1.0cm", "1.0cm", "1.0cm");

    array = new Geometria[]{esfera, cubo, cilindro, paralelepipedo};
    json = objectMapper.writeValueAsString(array);
    all = objectMapper.writeValueAsBytes(array);
    return all;
  }

  public static Geometria[] getArray() {
    instance();
    return array;
  }

  public static String getJson() {
    instance();
    return json;
  }
}
