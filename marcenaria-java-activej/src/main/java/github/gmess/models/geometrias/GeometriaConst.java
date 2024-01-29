package github.gmess.models.geometrias;

import java.io.IOException;
import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;

public abstract class GeometriaConst {
  public final static Geometria[] GEOMETRIAS = new Geometria[] { 
    new Esfera("Estrutura Esférica", "1,0cm"),
    new Cubo("Estrutura Cúbica", "1,0cm"),
    new Cilindro("Estrutura Cilindríca", "1,0cm", "1,0cm"),
    new Paralelepipedo("Estrutura Paralelepipedo", "1,0cm", "1,0cm", "1,0cm")
  };

  public static final byte[] BYTES;

  static {
    DslJson<Object> dslJson = new DslJson<>();
    JsonWriter writer = dslJson.newWriter();
    try {
      dslJson.serialize(writer, GEOMETRIAS);
      BYTES = writer.toByteArray();
      writer.reset();
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }
}
