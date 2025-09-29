package app.cta4j.train.external.follow;

public record CtaFollowCtatt(
    String tmst,
    String errCd,
    String errNm,
    CtaFollowPosition position,
    CtaFollowEta eta
) {
}
