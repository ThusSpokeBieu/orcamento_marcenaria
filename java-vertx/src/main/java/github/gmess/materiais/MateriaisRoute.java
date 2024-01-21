package github.gmess.materiais;

import github.gmess.RouteVerticle;
import github.gmess.http.HttpConst;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.openapi.Operation;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.RoutingContext;

public class MateriaisRoute {
  private final static Logger logger = LoggerFactory.getLogger(RouteVerticle.class.getName());

  public static void set(final RouterBuilder rb) {
    rb
      .operation("getMateriais")
      .handler(MateriaisRoute::handleRequest)
      .failureHandler(MateriaisRoute::handleError);
  }

  public static void handleRequest(final RoutingContext context) {
    context
      .response()
      .setStatusCode(200)
      .setStatusMessage("ok")
      .putHeader(HttpConst.HEADER_CONTENT_TYPE, HttpConst.RESPONSE_TYPE_JSON)
      .end(Material.json);
  }

  public static void handleError(final RoutingContext context) {
    context
      .response()
      .setStatusCode(500)
      .setStatusMessage("Somenthing went wrong")
      .end();

    logger.error(context.failure().getMessage());
  }
}
