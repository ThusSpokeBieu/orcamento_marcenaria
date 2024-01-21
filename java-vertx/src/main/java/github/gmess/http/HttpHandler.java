package github.gmess.http;

import github.gmess.geometrias.GeometriaHandler;
import github.gmess.materiais.MateriaisHandler;
import github.gmess.orcamento.OrcamentoHandler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import static github.gmess.http.HttpConst.PATH_GEOMETRIAS;
import static github.gmess.http.HttpConst.PATH_MATERIAIS;
import static github.gmess.http.HttpConst.PATH_ORCAMENTO;

public class HttpHandler implements Handler<HttpServerRequest> {

  private final static Logger logger = LoggerFactory.getLogger(HttpHandler.class.getName());

  private static <T> Handler<AsyncResult<T>> onSuccess(Handler<T> handler) {
    return ar -> {
      if (ar.succeeded()) {
        handler.handle(ar.result());
      }
    };
  }

  @Override
  public void handle(HttpServerRequest request) {
    try {
      switch (request.path()) {
        case PATH_MATERIAIS -> { MateriaisHandler.handle(request); }
        case PATH_GEOMETRIAS -> { GeometriaHandler.handle(request); }
        case PATH_ORCAMENTO -> {OrcamentoHandler.handle(request); }
        default -> { request.response().setStatusCode(404).end("Not Found"); }
      }
    } catch (Exception e) {
      sendError(request, e);
    }
  }

  private void sendError(HttpServerRequest req, Throwable cause) {
    logger.error(cause.getMessage(), cause);
    req.response().setStatusCode(500).end();
  }
}
