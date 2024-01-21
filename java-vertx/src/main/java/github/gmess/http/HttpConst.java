package github.gmess.http;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class HttpConst {
  public static final String PATH_MATERIAIS = "/materiais";
  public static final String PATH_GEOMETRIAS = "/geometrias";
  public static final String PATH_ORCAMENTO = "/orcamento";
  public static final CharSequence RESPONSE_TYPE_JSON = HttpHeaders.createOptimized("application/json");
  public static final CharSequence HEADER_SERVER = HttpHeaders.createOptimized("server");
  public static final CharSequence HEADER_DATE = HttpHeaders.createOptimized("date");
  public static final CharSequence HEADER_CONTENT_TYPE = HttpHeaders.createOptimized("content-type");
  public static final CharSequence HEADER_CONTENT_LENGTH = HttpHeaders.createOptimized("content-length");
  public static final CharSequence SERVER = HttpHeaders.createOptimized("marcenaria");
  public static final String HELLO_WORLD = "Hello, world!";
  public static final Buffer HELLO_WORLD_BUFFER = Buffer.buffer(HELLO_WORLD, "UTF-8");
  public static final CharSequence HELLO_WORLD_LENGTH = HttpHeaders.createOptimized("" + HELLO_WORLD.length());

  public static CharSequence createDateHeader() {
    return HttpHeaders.createOptimized(DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now()));
  }
}
