package github.com.marcenariaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableCaching
@EnableWebFlux
@EnableAsync
public class MarcenariaSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarcenariaSpringApplication.class, args);
  }
}
