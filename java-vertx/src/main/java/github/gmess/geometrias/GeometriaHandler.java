package github.gmess.geometrias;

import github.gmess.geometrias.estruturas.Esfera;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;

public abstract class GeometriaHandler {
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
      .end();
  }
}
