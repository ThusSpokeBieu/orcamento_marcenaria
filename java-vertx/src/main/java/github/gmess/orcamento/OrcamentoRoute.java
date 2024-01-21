package github.gmess.orcamento;

import github.gmess.moveis.Movel;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.openapi.RouterBuilder;

public class OrcamentoRoute {
  private final static Logger logger = LoggerFactory.getLogger(OrcamentoRoute.class.getName());

  public static void set(final RouterBuilder rb) {
    rb
      .operation("postOrcamento")
      .handler(OrcamentoRoute::handleRequest)
      .failureHandler(OrcamentoRoute::handleError);
  }

  public static void handleRequest(final RoutingContext context) {
    if (context.response().ended()) return;

    final Movel movel = new Movel(context.body().asJsonObject());

    if (!movel.isValid()) {
      handleBadRequest(context, movel);
      return;
    }

    context
      .response()
      .setStatusMessage("ok")
      .setStatusCode(200)
      .end(movel.getResponse());
  }

  public static void handleError(final RoutingContext context) {
    if (context.response().ended()) return;

    context
      .response()
      .setStatusCode(500)
      .setStatusMessage("Somenthing went wrong")
      .end();

    context.failure().printStackTrace();

    logger.error(context.failure().getMessage());
  }

  public static void handleBadRequest(final RoutingContext context, final Movel movel) {
    if (context.response().ended()) return;

    context
      .response()
      .setStatusCode(400)
      .setStatusMessage("Bad Request")
      .end(movel.getErr());

    context.failure().printStackTrace();

    logger.error(context.failure().getMessage());
  }
}