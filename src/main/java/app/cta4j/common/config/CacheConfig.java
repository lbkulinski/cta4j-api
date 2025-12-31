package app.cta4j.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final List<String> CACHE_NAMES = List.of(
        "routes",
        "directions",
        "stops",
        "stations"
    );

    @Bean
    public Caffeine<Object, Object> caffeine() {
        return Caffeine.newBuilder()
                       .expireAfterAccess(1L, TimeUnit.DAYS);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(CACHE_NAMES);

        return cacheManager;
    }
}
