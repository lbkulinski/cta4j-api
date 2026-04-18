package com.cta4j.api.aws.dto;

import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public record Secret(
    RollbarSecret rollbar,
    MetroSecret metro,
    CtaSecret cta
) {
    public Secret {
        Objects.requireNonNull(rollbar, "rollbar must not be null");
        Objects.requireNonNull(metro, "metro must not be null");
        Objects.requireNonNull(cta, "cta must not be null");
    }

    @Override
    public String toString() {
        return "Secret{rollbar=REDACTED, metro=REDACTED, cta=REDACTED}";
    }

    public record RollbarSecret(String accessToken) {
        public RollbarSecret {
            Objects.requireNonNull(accessToken, "accessToken must not be null");
        }
    }

    public record MetroSecret(
        String apiKey,
        String secondaryApiKey
    ) {
        public MetroSecret {
            Objects.requireNonNull(apiKey, "apiKey must not be null");
            Objects.requireNonNull(secondaryApiKey, "secondaryApiKey must not be null");
        }
    }

    public record CtaSecret(
        String trainApiKey,
        String busApiKey
    ) {
        public CtaSecret {
            Objects.requireNonNull(trainApiKey, "trainApiKey must not be null");
            Objects.requireNonNull(busApiKey, "busApiKey must not be null");
        }
    }
}
