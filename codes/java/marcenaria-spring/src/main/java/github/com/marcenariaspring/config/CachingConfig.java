package github.com.marcenariaspring.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.Set;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CachingConfig {
  @Bean
  public Caffeine caffeine() {
    return Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(2));
  }

  @Bean
  public CacheManager cacheManager(Caffeine caffeine) {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    final var names = Set.of("orcamento", "materiais", "geometrias");
    cacheManager.setAsyncCacheMode(true);
    cacheManager.setCaffeine(caffeine);
    cacheManager.setCacheNames(names);
    return cacheManager;
  }
}
