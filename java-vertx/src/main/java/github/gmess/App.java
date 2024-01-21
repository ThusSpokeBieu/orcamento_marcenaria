package github.gmess;

import io.netty.util.concurrent.MultithreadEventExecutorGroup;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class App  {
    public final static int PORT = 8888;
    private final static Logger logger = LoggerFactory.getLogger(App.class.getName());

    public static void main( String[] args ) throws Exception {
        int eventLoopPoolSize = 1;// VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE;

        Vertx vertx = Vertx.vertx(
          new VertxOptions()
            .setEventLoopPoolSize(eventLoopPoolSize)
            .setPreferNativeTransport(true));

        vertx.exceptionHandler(err -> {
            err.printStackTrace();
        });

        printInfo(vertx);

        vertx.deployVerticle(
          RouteVerticle.class.getName(),

          new DeploymentOptions()
            .setHa(true)
            .setWorkerPoolSize(eventLoopPoolSize)
            .setInstances(eventLoopPoolSize)
            .setMaxWorkerExecuteTimeUnit(TimeUnit.SECONDS),

          event -> {
              if (event.succeeded()) {
                  logger.info("Server listening on port " + App.PORT);
              } else {
                  logger.error("Unable to start your application", event.cause());
              }
          });
    }

    private static void printInfo(Vertx vertx) {
        boolean nativeTransport = vertx.isNativeTransportEnabled();
        String transport = ((VertxInternal) vertx).transport().getClass().getSimpleName();
        String version = "unknown";

        try {
            InputStream in = Vertx.class
              .getClassLoader()
              .getResourceAsStream("META-INF/vertx/vertx-version.txt");

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
        logger.info("Event Loop Size: " + ((MultithreadEventExecutorGroup)vertx.nettyEventLoopGroup()).executorCount());
        logger.info("Native transport : " + nativeTransport);
        logger.info("Transport : " + transport);
    }

}
