package app.cta4j.common.api.external.train.arrival;

import java.util.List;

public record CtaArrivalsCtatt(
    String tmst,
    String errCd,
    String errNm,
    List<CtaArrivalsEta> eta
) {
}
