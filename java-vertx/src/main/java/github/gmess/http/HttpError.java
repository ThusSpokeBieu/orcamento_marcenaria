package github.gmess.http;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import static java.lang.StringTemplate.STR;

public class HttpError extends JsonObject {

  public final static String JSON_STRING = STR."""
                                              {
                                                "status": 404,
                                                "message": "Bad Request",
                                                "errors": [
                                                ]
                                              }
                                              """.trim();

  public HttpError(final String json) {
    super(json);
  }

  public static HttpError create() {
    return new HttpError(JSON_STRING);
  }

  public HttpError initOrAdd(final String error) {
    final var map = super.getMap();
    if (map.isEmpty()) {
      map.put("status", 400);
      map.put("message", "Bad Request");
      map.put("errors", new JsonArray());
    }

    super.getJsonArray("errors").add(error);
    return this;
  }

  public boolean hasError() {
    return this.getJsonArray("errors").isEmpty();
  }

}
