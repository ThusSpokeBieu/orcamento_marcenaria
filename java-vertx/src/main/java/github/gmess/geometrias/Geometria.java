package github.gmess.geometrias;

import com.fasterxml.jackson.core.JsonProcessingException;
import github.gmess.utils.StringUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.HashSet;
import java.util.List;

public abstract class Geometria {

  protected String nome;
  protected Double area;
  protected JsonObject json;
  protected HashSet<Error> errors = new HashSet<>();

  public String getNome() {
    return nome;
  }
  public Double getArea() {
    return area;
  }

  public JsonObject getJson() {
    return json;
  }

}
