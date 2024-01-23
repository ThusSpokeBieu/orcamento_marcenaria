package github.gmess;

import github.gmess.materiais.MateriaisRoute;
import github.gmess.orcamento.OrcamentoRoute;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.Router;

public class RouteVerticle extends AbstractVerticle {
  private final static Logger logger = LoggerFactory.getLogger(RouteVerticle.class.getName());

  private HttpServer server;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    RouterBuilder
      .create(vertx, "src/main/resources/openapi.yaml")
      .onSuccess(this::routes)
      .onFailure(err -> err.printStackTrace());
  }

  public void routes(final RouterBuilder rb) {
    MateriaisRoute.set(rb);
    OrcamentoRoute.set(rb);

    configServer(rb);
  }

  public void configServer(final RouterBuilder rb) {
    server = vertx
      .createHttpServer(
        new HttpServerOptions()
          .setUseAlpn(true)
          .setAcceptUnmaskedFrames(true)
          .setReusePort(true)
          .setReuseAddress(true)
          .setTcpNoDelay(true)
          .setTcpFastOpen(true)
          .setTcpKeepAlive(true)
          .setReadIdleTimeout(0));

      Router router = rb.createRouter();

      router.route("/swagger*")
            .handler(StaticHandler.create("src/main/resources/webroot/swagger/"));

      logger.info("\"\uD83D\uDE80 Servidor iniciado na porta: " + App.PORT + "-> Acesse http://localhost:" + App.PORT);

      server
        .requestHandler(router)
            .listen(App.PORT);
  }
}
