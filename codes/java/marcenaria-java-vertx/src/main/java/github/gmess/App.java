package github.gmess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import io.netty.util.concurrent.MultithreadEventExecutorGroup;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.mutiny.core.Vertx;

public class App {
  public static final int PORT = 8080;
  private static final Logger logger = LoggerFactory.getLogger(App.class.getName());

  public static void main(String[] args) throws Exception {
    int eventLoopPoolSize = VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE;

    long startTime = System.currentTimeMillis();

    Vertx vertx = Vertx.vertx(
        new VertxOptions().setEventLoopPoolSize(eventLoopPoolSize).setPreferNativeTransport(true));

    vertx.exceptionHandler(err -> {
      err.printStackTrace();
    });

    printInfo(vertx);

    logger.info("🚀 Starting Vert.x");

    vertx.deployVerticle(MainVerticle.class.getName(),
        new DeploymentOptions().setHa(true).setWorkerPoolSize(eventLoopPoolSize)
            .setInstances(eventLoopPoolSize).setThreadingModel(ThreadingModel.VIRTUAL_THREAD)
            .setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS))
        .subscribe().with(ok -> {
          long vertxTime = System.currentTimeMillis();
          logger.info("✅ Deployment success");
          logger.info("💡 Vert.x app started in " + (vertxTime - startTime) + "ms");
        }, err -> logger.error("🔥 Deployment failure", err));
  }

  private static void printInfo(Vertx vertx) {
    boolean nativeTransport = vertx.isNativeTransportEnabled();
    String version = "unknown";

    try {
      InputStream in =
          Vertx.class.getClassLoader().getResourceAsStream("META-INF/vertx/vertx-version.txt");

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      byte[] buffer = new byte[256];
      while (true) {
        int amount = in.read(buffer);
        if (amount == -1) {
          break;
        }
        out.write(buffer, 0, amount);
      }
      version = out.toString();
    } catch (IOException e) {
      logger.error("Could not read Vertx version", e);;
    }

    logger.info("Vertx: " + version);
    logger.info("Event Loop Size: "
        + ((MultithreadEventExecutorGroup) vertx.nettyEventLoopGroup()).executorCount());
    logger.info("Native transport : " + nativeTransport);
  }
}
