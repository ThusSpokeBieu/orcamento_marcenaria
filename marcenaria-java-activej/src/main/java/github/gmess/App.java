package github.gmess;

import github.gmess.models.Movel;
import github.gmess.models.Orcamento;
import github.gmess.models.geometrias.GeometriaConst;
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
import io.activej.launchers.http.HttpServerLauncher;
import io.activej.reactor.Reactor;

public class App extends HttpServerLauncher {

  @Override
  protected Module getBusinessLogicModule() {
    return Modules.combine(new CodecsModule());
  }
  
  @Provides
	AsyncServlet servlet(
    final Reactor reactor, 
    final JsonCodec<Movel> movelJsonCodec,
    final JsonCodec<Orcamento> orcamentoJsonCodec,
    final GeometriaConst GEOMETRIAS) {
		
    return RoutingServlet.builder(reactor)

      .with(HttpMethod.GET, "/geometrias", $ -> 
               HttpResponse.ok200()
                    .withBody(GEOMETRIAS.BYTES)
                    .toPromise()
      )
        
      .with(HttpMethod.POST, "/orcamento", request -> request.loadBody()
          .then($ -> {
            ByteBuf body = request.getBody();
            
            try {
			        byte[] bodyBytes = body.getArray();
			        Movel movel = JsonUtils.fromJsonBytes(movelJsonCodec, bodyBytes);
              Orcamento orcamento = Orcamento.from(movel);
			         
              return HttpResponse.ok200()
                .withJson(JsonUtils.toJson(orcamentoJsonCodec, orcamento))
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
