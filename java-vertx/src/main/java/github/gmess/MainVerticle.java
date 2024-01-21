package github.gmess;

import github.gmess.http.HttpHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.OpenSSLEngineOptions;

import static github.gmess.http.HttpConst.HEADER_CONTENT_LENGTH;
import static github.gmess.http.HttpConst.HEADER_CONTENT_TYPE;
import static github.gmess.http.HttpConst.HEADER_DATE;
import static github.gmess.http.HttpConst.HEADER_SERVER;
import static github.gmess.http.HttpConst.HELLO_WORLD_LENGTH;
import static github.gmess.http.HttpConst.RESPONSE_TYPE_JSON;
import static github.gmess.http.HttpConst.SERVER;
import static github.gmess.http.HttpConst.createDateHeader;

public class MainVerticle extends AbstractVerticle {
  private HttpServer server;
  private CharSequence dateString;
  private CharSequence[] headers;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    server = vertx
      .createHttpServer(
        new HttpServerOptions()
          .setAcceptUnmaskedFrames(true)
          .setReusePort(true)
          .setReuseAddress(true)
          .setTcpNoDelay(true)
          .setTcpFastOpen(true)
          .setTcpKeepAlive(true)
          .setSsl(true)
          .setUseAlpn(false)
          .setSslEngineOptions(new OpenSSLEngineOptions())
          .setReadIdleTimeout(0))

      .requestHandler(new HttpHandler());

    dateString = createDateHeader();

    headers =  new CharSequence[] {
      HEADER_CONTENT_TYPE, RESPONSE_TYPE_JSON,
      HEADER_SERVER, SERVER,
      HEADER_DATE, dateString,
      HEADER_CONTENT_LENGTH, HELLO_WORLD_LENGTH };

    vertx.setPeriodic(1000, id -> headers[5] = dateString = createDateHeader());

    server.listen(App.PORT)
          .<Void>mapEmpty()
          .onComplete(startPromise);
  }
}
