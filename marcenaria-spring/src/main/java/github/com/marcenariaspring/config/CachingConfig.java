package github.com.marcenariaspring.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CachingConfig {
  @Bean
  public Caffeine caffeine() {
    return Caffeine.newBuilder();
  }

  @Bean
  public CacheManager cacheManager(Caffeine caffeine) {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    cacheManager.setAsyncCacheMode(true);
    cacheManager.setCaffeine(caffeine);
    return cacheManager;
  }
}
