package github.gmess;

import github.gmess.models.MovelList;
import github.gmess.models.OrcamentoList;
import github.gmess.modules.CodecsModule;
import io.activej.bytebuf.ByteBuf;
import io.activej.common.exception.MalformedDataException;
import io.activej.http.AsyncServlet;
import io.activej.http.HttpMethod;
import io.activej.http.HttpResponse;
import io.activej.http.RoutingServlet;
import io.activej.inject.annotation.Provides;
import io.activej.inject.module.Module;
import io.activej.inject.module.Modules;
import io.activej.json.JsonCodec;
import io.activej.json.JsonUtils;
import io.activej.launchers.http.MultithreadedHttpServerLauncher;
import io.activej.reactor.Reactor;
import io.activej.worker.annotation.Worker;

public class App extends MultithreadedHttpServerLauncher {
  public static final int WORKERS = 16;

  @Override
  @Worker
  protected Module getBusinessLogicModule() {
    return Modules.combine(new CodecsModule());
  }

  @Provides
  @Worker
  AsyncServlet servlet(
      final Reactor reactor,
      final JsonCodec<MovelList> movelJsonCodec,
      final JsonCodec<OrcamentoList> orcamentoJsonCodec
      /* final GeometriaConst GEOMETRIAS */ ) {

    return RoutingServlet.builder(reactor)
        /*
         * .with( HttpMethod.GET, "/geometrias", $ ->
         * HttpResponse.ok200().withBody(GEOMETRIAS.BYTES).toPromise())
         */
        .with(
            HttpMethod.POST,
            "/orcamentos",
            request ->
                request
                    .loadBody()
                    .then(
                        $ -> {
                          ByteBuf body = request.getBody();

                          try {
                            byte[] bodyBytes = body.getArray();
                            MovelList moveis = JsonUtils.fromJsonBytes(movelJsonCodec, bodyBytes);
                            OrcamentoList orcamentos = OrcamentoList.from(moveis);

                            return HttpResponse.ok200()
                                .withJson(JsonUtils.toJson(orcamentoJsonCodec, orcamentos))
                                .toPromise();

                          } catch (MalformedDataException e) {
                            return HttpResponse.ofCode(400).withBody(e.getMessage()).toPromise();
                          }
                        }))
        .build();
  }

  public static void main(String[] args) throws Exception {
    var app = new App();
    app.launch(args);
  }
}
