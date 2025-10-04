package app.cta4j.common.api.external.train.follow;

public record CtaFollowPosition(
    String lat,
    String lon,
    String heading
) {
}
