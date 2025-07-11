package app.cta4j.service;

import app.cta4j.dto.Secret;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Objects;

@Service
public class SecretService {
    private final Environment env;

    private final SecretsManagerClient secretsManagerClient;

    private final ObjectMapper objectMapper;

    @Autowired
    public SecretService(Environment env, SecretsManagerClient secretsManagerClient, ObjectMapper objectMapper) {
        this.env = Objects.requireNonNull(env);

        this.secretsManagerClient = Objects.requireNonNull(secretsManagerClient);

        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public Secret getSecret() {
        String secretId = this.env.getRequiredProperty("app.aws.secrets-manager.secret-id");

        GetSecretValueRequest request = GetSecretValueRequest.builder()
                                                             .secretId(secretId)
                                                             .build();

        GetSecretValueResponse response = this.secretsManagerClient.getSecretValue(request);

        String secretString = response.secretString();

        if ((secretString == null) || secretString.isEmpty()) {
            throw new IllegalStateException("Secret value is empty or null");
        }

        Secret secret;

        try {
            secret = this.objectMapper.readValue(secretString, Secret.class);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse secret value", e);
        }

        return secret;
    }
}
