package com.cta4j.api.aws.client;

import com.cta4j.api.aws.dto.Secret;
import com.cta4j.api.aws.exception.SecretException;
import com.amazonaws.secretsmanager.caching.SecretCache;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.json.JsonMapper;

@Component
@NullMarked
public final class SecretsClient {
    private final SecretCache secretCache;
    private final JsonMapper jsonMapper;

    private final String secretId;

    @Autowired
    public SecretsClient(
        SecretCache secretCache,
        JsonMapper jsonMapper,
        @Value("${app.aws.secrets-manager.secret-id}") String secretId
    ) {
        this.secretCache = secretCache;
        this.jsonMapper = jsonMapper;
        this.secretId = secretId;
    }

    public Secret getSecret() {
        String secretString = this.secretCache.getSecretString(this.secretId);

        if (secretString == null || secretString.isEmpty()) {
            throw new SecretException("Secret value is empty or null");
        }

        Secret secret;

        try {
            secret = this.jsonMapper.readValue(secretString, Secret.class);
        } catch (Exception e) {
            throw new SecretException("Failed to parse secret value", e);
        }

        return secret;
    }
}
