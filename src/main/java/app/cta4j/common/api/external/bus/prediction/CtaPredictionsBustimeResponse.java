package app.cta4j.common.api.external.bus.prediction;

import java.util.List;

public record CtaPredictionsBustimeResponse(
    List<CtaPredictionsPrd> prd
) {
}
