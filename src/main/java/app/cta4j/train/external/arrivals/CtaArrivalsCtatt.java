package app.cta4j.train.external.arrivals;

import java.util.List;

public record CtaArrivalsCtatt(
    String tmst,
    String errCd,
    String errNm,
    List<CtaArrivalsEta> eta
) {
}
