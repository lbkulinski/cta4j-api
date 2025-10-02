package app.cta4j.api.external.train.arrivals;

import java.util.List;

public record CtaArrivalsCtatt(
    String tmst,
    String errCd,
    String errNm,
    List<CtaArrivalsEta> eta
) {
}
