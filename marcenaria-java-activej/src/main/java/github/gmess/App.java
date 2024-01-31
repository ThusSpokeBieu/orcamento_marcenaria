package github.gmess;

import github.gmess.codecs.EstruturaValorCodecs;
import github.gmess.codecs.MovelCodecs;
import github.gmess.codecs.OrcamentoCodecs;
import github.gmess.codecs.geometrias.CilindroCodecs;
import github.gmess.codecs.geometrias.CuboCodecs;
import github.gmess.codecs.geometrias.EsferaCodecs;
import github.gmess.codecs.geometrias.GeometriaCodecs;
import github.gmess.codecs.geometrias.ParalelepipedoCodecs;
import github.gmess.models.EstruturaValor;
import github.gmess.models.Movel;
import github.gmess.models.Orcamento;
import github.gmess.models.geometrias.Cilindro;
import github.gmess.models.geometrias.Cubo;
import github.gmess.models.geometrias.Esfera;
import github.gmess.models.geometrias.Geometria;
import github.gmess.models.geometrias.GeometriaConst;
import github.gmess.models.geometrias.Paralelepipedo;
import io.activej.bytebuf.ByteBuf;
import io.activej.common.exception.MalformedDataException;
import io.activej.http.AsyncServlet;
import io.activej.http.HttpMethod;
import io.activej.http.HttpResponse;
import io.activej.http.RoutingServlet;
import io.activej.inject.Injector;
import io.activej.inject.annotation.Provides;
import io.activej.json.JsonCodec;
import io.activej.json.JsonUtils;
import io.activej.launchers.http.HttpServerLauncher;
import io.activej.reactor.Reactor;

public class App extends HttpServerLauncher {
  @Provides
  public JsonCodec<Esfera> esferaCodec() {
    return EsferaCodecs.create();
  }

  @Provides
  public JsonCodec<Cubo> cuboCodec() {
    return CuboCodecs.create();
  }

  @Provides
  public JsonCodec<Cilindro> cilindroCodec() {
    return CilindroCodecs.create();
  }

  @Provides
  public JsonCodec<Paralelepipedo> paralelepipedoCodec() {
    return ParalelepipedoCodecs.create();
  }

  @Provides
  public JsonCodec<EstruturaValor> estruturaValorCodec() {
    return EstruturaValorCodecs.create();
  }

  @Provides
  public JsonCodec<Orcamento> orcamentoCodec(final JsonCodec<EstruturaValor> estruturaValorCodec) {
    return OrcamentoCodecs.create(estruturaValorCodec);
  }

  @Provides
  public JsonCodec<Geometria> geometriaCodec(
    final JsonCodec<Esfera> esferaJsonCodec,
    final JsonCodec<Cubo> cuboJsonCodec,
    final JsonCodec<Cilindro> cilindroJsonCodec,
    final JsonCodec<Paralelepipedo> paralelepipedoJsonCodec
  ) {
    return GeometriaCodecs.create(esferaJsonCodec, cuboJsonCodec, cilindroJsonCodec, paralelepipedoJsonCodec);
  }

  @Provides
  public JsonCodec<Movel> movelCodec(
    final JsonCodec<Geometria> geometriaCodec
  ) {
    return MovelCodecs.create(geometriaCodec);
  }

  @Provides
  public GeometriaConst geometriaConst(
    final JsonCodec<Geometria> geometriaCodec
  ) {
    return new GeometriaConst(geometriaCodec);
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
    Injector.useSpecializer();
		var app = new App();
		app.launch(args);
	}

}
