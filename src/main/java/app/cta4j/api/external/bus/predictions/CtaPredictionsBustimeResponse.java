package app.cta4j.api.external.bus.predictions;

import java.util.List;

public record CtaPredictionsBustimeResponse(
    List<CtaPredictionsPrd> prd
) {
}
