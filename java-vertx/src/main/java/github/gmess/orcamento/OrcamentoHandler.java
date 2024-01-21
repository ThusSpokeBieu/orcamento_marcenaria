package github.gmess.orcamento;

import github.gmess.utils.validators.JsonValidator;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;

public abstract class OrcamentoHandler {
  private final static Logger logger = LoggerFactory.getLogger(OrcamentoHandler.class.getName());

  public static void handle(final HttpServerRequest req) {

    if (!req.method().equals(HttpMethod.POST)) {
      req
        .response()
        .setStatusCode(404)
        .end("Method is not allowed");

      return;
    }

    req.body()
      .onSuccess(b -> OrcamentoHandler.validateJson(b, req))
      .onFailure(e -> OrcamentoHandler.handleError(e, req));
  }

  private static void validateJson(final Buffer body, final HttpServerRequest req) {
    var json = body.toJsonObject();
    var result = JsonValidator.getValidator().validate(json);

    if (result.getValid()) {
      handleJson(json, req);
      return;
    }

    req.response()
       .setStatusCode(404)
       .setStatusMessage("Bad Request")
       .end(result.getErrors().toString());

  }

  private static void handleJson(final JsonObject body, final HttpServerRequest req) {

    req
      .response()
      .setStatusCode(200)
      .end(body.toBuffer());
  }

  private static void handleError(final Throwable err, final HttpServerRequest req) {
    req.response().setStatusCode(500).end(err.getLocalizedMessage());
    logger.error(err);
    err.printStackTrace();
  }

}
