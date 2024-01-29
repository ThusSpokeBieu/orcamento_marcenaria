package github.gmess;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonReader;
import com.dslplatform.json.JsonWriter;
import github.gmess.models.Movel;
import github.gmess.models.Orcamento;
import github.gmess.models.geometrias.GeometriaConst;
import io.activej.http.AsyncServlet;
import io.activej.http.ContentTypes;
import io.activej.http.HttpHeaderValue;
import io.activej.http.HttpHeaderValue.HttpHeaderValueOfContentType;
import io.activej.http.HttpHeaders;
import io.activej.http.HttpMethod;
import io.activej.http.HttpResponse;
import io.activej.http.RoutingServlet;
import io.activej.inject.annotation.Provides;
import io.activej.launchers.http.HttpServerLauncher;
import io.activej.reactor.Reactor;

public class App extends HttpServerLauncher {

  @Provides
  public DslJson<Object> json() {
    return new DslJson<>();
  }

  @Provides
  public JsonWriter jsonWriter(final DslJson<Object> dslJson) {
    return dslJson.newWriter();
  }

  @Provides
  public JsonReader<Object> jsonReader(
    final DslJson<Object> dslJson) {
    return dslJson.newReader();
  }

  @Provides
  public HttpHeaderValue jsonType() {
    return HttpHeaderValueOfContentType.ofContentType(ContentTypes.JSON_UTF_8);
  }

	@Provides
	AsyncServlet servlet(
    final Reactor reactor, 
    final DslJson<Object> json,
    final JsonReader<Object> reader,
    final JsonWriter writer,
    final HttpHeaderValue contentType) {
		return RoutingServlet.builder(reactor)
      .with(HttpMethod.GET, "/geometrias", $ -> HttpResponse.ok200()
                 .withHeader(HttpHeaders.CONTENT_TYPE, contentType)
                 .withBody(GeometriaConst.BYTES)
                 .toPromise())
      .with(HttpMethod.POST, "/orcamento", request -> request.loadBody()
          .then(body -> {
            writer.reset();
            Movel movel = reader.process(body.getArray(), body.getArray().length)
                            .next(Movel.class);
            Orcamento orcamento = Orcamento.from(movel);
            json.serialize(writer, orcamento);
            
            return HttpResponse.ok200()
                    .withHeader(HttpHeaders.CONTENT_TYPE, contentType)
                    .withBody(writer.toByteArray())
                    .toPromise();
      }))
      .build();
	}

	public static void main(String[] args) throws Exception {
		var app = new App();
		app.launch(args);
	}

}
