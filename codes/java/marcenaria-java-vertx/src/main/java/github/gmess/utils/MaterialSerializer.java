package github.gmess.utils;

import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import github.gmess.models.Material;
import java.io.IOException;

public abstract class MaterialSerializer {
  public static Material read(JsonReader reader) throws IOException {
    if (reader.wasNull()) return null;
    final var result = Material.from(reader.readString());

    if (result.isLeft()) return result.getLeft();

    throw new IOException(result.get().message());
  }

  public static void write(JsonWriter writer, Material value) {
    if (value == null) {
      writer.writeNull();
    } else {
      writer.writeAscii(value.getNomeAsString());
    }
  }
}
