package com.cta4j.api.aws.client;

import com.cta4j.api.aws.dto.Secret;
import com.cta4j.api.aws.exception.SecretException;
import com.amazonaws.secretsmanager.caching.SecretCache;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Component
@NullMarked
public final class AwsSecretsClient {
    private final SecretCache secretCache;
    private final ObjectMapper objectMapper;

    private final String secretId;

    @Autowired
    public AwsSecretsClient(
        SecretsManagerClient secretsManagerClient,
        ObjectMapper objectMapper,
        @Value("${app.aws.secrets-manager.secret-id}") String secretId
    ) {
        this.secretCache = new SecretCache(secretsManagerClient);
        this.objectMapper = objectMapper;
        this.secretId = secretId;
    }

    public Secret getSecret() {
        String secretString = this.secretCache.getSecretString(this.secretId);

        if ((secretString == null) || secretString.isEmpty()) {
            throw new SecretException("Secret value is empty or null");
        }

        Secret secret;

        try {
            secret = this.objectMapper.readValue(secretString, Secret.class);
        } catch (Exception e) {
            throw new SecretException("Failed to parse secret value", e);
        }

        return secret;
    }
}
