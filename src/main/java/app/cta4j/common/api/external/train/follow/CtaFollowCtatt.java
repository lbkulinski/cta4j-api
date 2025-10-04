package app.cta4j.common.api.external.train.follow;

import java.util.List;

public record CtaFollowCtatt(
    String tmst,
    String errCd,
    String errNm,
    CtaFollowPosition position,
    List<CtaFollowEta> eta
) {
}
