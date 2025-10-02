package app.cta4j.client.external.follow;

import java.util.List;

public record CtaFollowCtatt(
    String tmst,
    String errCd,
    String errNm,
    CtaFollowPosition position,
    List<CtaFollowEta> eta
) {
}
