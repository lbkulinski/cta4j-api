package com.cta4j.api.aws.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@NullMarked
public record Secret(
    RollbarSecret rollbar,
    CtaSecret cta
) {
    public Secret {
        Objects.requireNonNull(rollbar);
        Objects.requireNonNull(cta);
    }

    public record RollbarSecret(String accessToken) {
        public RollbarSecret {
            Objects.requireNonNull(accessToken);
        }

        @Override
        public String toString() {
            return "RollbarSecret{accessToken=****}";
        }
    }

    public record CtaSecret(
        String trainApiKey,
        String busApiKey
    ) {
        public CtaSecret {
            Objects.requireNonNull(trainApiKey);
            Objects.requireNonNull(busApiKey);
        }

        @Override
        public String toString() {
            return "CtaSecret{trainApiKey=****, busApiKey=****}";
        }
    }
}
