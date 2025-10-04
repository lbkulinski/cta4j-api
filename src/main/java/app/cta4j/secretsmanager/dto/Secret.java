package app.cta4j.secretsmanager.dto;

public record Secret(
    MetroSecret metro,

    CtaSecret cta
) {
    public record MetroSecret(String apiKey, String secondaryApiKey) {}

    public record CtaSecret(String trainApiKey, String busApiKey) {}
}
