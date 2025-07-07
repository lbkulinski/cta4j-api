package app.cta4j.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfiguration {
    @Bean
    public Caffeine<Object, Object> buildCaffeine() {
        return Caffeine.newBuilder()
                       .expireAfterAccess(1L, TimeUnit.DAYS);
    }

    @Bean
    public CacheManager buildCacheManager(Caffeine<Object, Object> caffeine) {
        Objects.requireNonNull(caffeine);

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCaffeine(caffeine);

        return cacheManager;
    }
}
