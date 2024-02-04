package github.gmess;

import static github.gmess.App.PORT;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import github.gmess.models.Movel;
import github.gmess.models.MovelList;
import github.gmess.models.Orcamento;
import github.gmess.models.OrcamentoList;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.mutiny.core.http.HttpHeaders;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.RoutingContext;
import io.vertx.mutiny.ext.web.handler.BodyHandler;
import java.io.IOException;

public class MainVerticle extends AbstractVerticle {

  Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  final DslJson<Object> dslJson = new DslJson<>();
  final JsonWriter writer = dslJson.newWriter();
  final JsonReader<Object> reader = dslJson.newReader();

  static final CharSequence JSON_CONTENT_TYPE = HttpHeaders.createOptimized("application/json");
  static final CharSequence CONTENT_TYPE = HttpHeaders.createOptimized("Content-Type");

  static final String STR_JSON_CONTENT_TYPE = JSON_CONTENT_TYPE.toString();

  @Override
  public Uni<Void> asyncStart() {
    Router router = Router.router(vertx);

    BodyHandler bodyHandler = BodyHandler.create();

    router
        .post("/orcamentos")
        .handler(bodyHandler::handle)
        .produces(STR_JSON_CONTENT_TYPE)
        .consumes(STR_JSON_CONTENT_TYPE)
        .respond(this::createOrcamentos);

    router
        .post("/orcamento-un")
        .handler(bodyHandler::handle)
        .produces(STR_JSON_CONTENT_TYPE)
        .consumes(STR_JSON_CONTENT_TYPE)
        .respond(this::createOrcamento);

    Uni<HttpServer> startHttpServer =
        vertx
            .createHttpServer()
            .requestHandler(router)
            .listen(PORT)
            .onItem()
            .invoke(() -> logger.info("🚀 ✅ HTTP Server listening on port " + PORT));

    return Uni.combine().all().unis(startHttpServer).discardItems();
  }

  private Uni<String> createOrcamentos(RoutingContext ctx) {

    byte[] payload = ctx.body().buffer().getBytes();

    try {
      return Uni.createFrom()
          .item(payload)
          .map(
              p -> {
                try {
                  return reader.process(p, p.length).next(MovelList.class);
                } catch (IOException e) {
                  throw new RuntimeException("Error processing JSON input", e);
                }
              })
          .map(OrcamentoList::from)
          .map(
              orcamento -> {
                try {
                  writer.reset();
                  dslJson.serialize(writer, orcamento);

                  return writer.toString();
                } catch (IOException e) {
                  throw new RuntimeException("Error serializing JSON", e);
                }
              });
    } catch (Exception e) {
      return Uni.createFrom().failure(e);
    }
  }

  private Uni<String> createOrcamento(RoutingContext ctx) {

    byte[] payload = ctx.body().buffer().getBytes();

    try {
      return Uni.createFrom()
          .item(payload)
          .map(
              p -> {
                try {
                  return reader.process(p, p.length).next(Movel.class);
                } catch (IOException e) {
                  throw new RuntimeException("Error processing JSON input", e);
                }
              })
          .map(Orcamento::from)
          .map(
              orcamento -> {
                try {
                  writer.reset();
                  dslJson.serialize(writer, orcamento);

                  return writer.toString();
                } catch (IOException e) {
                  throw new RuntimeException("Error serializing JSON", e);
                }
              });
    } catch (Exception e) {
      return Uni.createFrom().failure(e);
    }
  }
}
