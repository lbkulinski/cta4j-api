package app.cta4j.aws.dto;

public record Secret(
    RollbarSecret rollbar,
    MetroSecret metro,
    CtaSecret cta
) {
    public record RollbarSecret(String accessToken) {}

    public record MetroSecret(String apiKey, String secondaryApiKey) {}

    public record CtaSecret(String trainApiKey, String busApiKey) {}
}
