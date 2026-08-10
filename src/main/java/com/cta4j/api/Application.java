package com.cta4j.api;

import com.cta4j.api.aws.config.DynamoDbProperties;
import com.cta4j.api.common.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CorsProperties.class, DynamoDbProperties.class})
public class Application {
    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
