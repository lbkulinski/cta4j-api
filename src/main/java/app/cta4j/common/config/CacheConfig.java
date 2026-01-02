package app.cta4j.common.config;

import app.cta4j.common.config.properties.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CacheProperties cacheProperties;

    @Autowired
    public CacheConfig(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    public Caffeine<Object, Object> caffeine() {
        long ttlSeconds = this.cacheProperties.getTtlSeconds();

        return Caffeine.newBuilder()
                       .expireAfterAccess(ttlSeconds, TimeUnit.SECONDS);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        List<String> cacheNames = this.cacheProperties.getNames();

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(cacheNames);

        return cacheManager;
    }
}
