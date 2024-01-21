package github.gmess.materiais;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import java.util.function.Consumer;

public abstract class MateriaisHandler {
  public static void handle(final HttpServerRequest req) {

    if (!req.method().equals(HttpMethod.GET)) {
      req
        .response()
        .setStatusCode(404)
        .end("Method is not allowed");

      return;
    }

    req
      .response()
      .setStatusCode(200)
      .end(Material.json);
  }

}
